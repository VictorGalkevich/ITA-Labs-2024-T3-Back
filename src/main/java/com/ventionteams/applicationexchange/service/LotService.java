package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.create.LotFilterDTO;
import com.ventionteams.applicationexchange.dto.create.LotUpdateDTO;
import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
import com.ventionteams.applicationexchange.dto.read.BidReadDto;
import com.ventionteams.applicationexchange.dto.read.LotReadDTO;
import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.LotSortCriteria;
import com.ventionteams.applicationexchange.entity.User;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.LengthUnit;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.exception.EntityStatusViolationException;
import com.ventionteams.applicationexchange.exception.PermissionsDeniedException;
import com.ventionteams.applicationexchange.exception.UserNotRegisteredException;
import com.ventionteams.applicationexchange.mapper.BidMapper;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.BidRepository;
import com.ventionteams.applicationexchange.repository.CategoryRepository;
import com.ventionteams.applicationexchange.repository.LotRepository;
import com.ventionteams.applicationexchange.repository.UserRepository;
import com.ventionteams.applicationexchange.specification.LotSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ventionteams.applicationexchange.entity.enumeration.LotStatus.ACTIVE;
import static com.ventionteams.applicationexchange.entity.enumeration.LotStatus.AUCTION_ENDED;
import static com.ventionteams.applicationexchange.entity.enumeration.LotStatus.CANCELLED;
import static com.ventionteams.applicationexchange.entity.enumeration.LotStatus.EXPIRED;
import static com.ventionteams.applicationexchange.entity.enumeration.LotStatus.MODERATED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@TransactionalService
@RequiredArgsConstructor
public class LotService extends UserItemService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LotMapper lotMapper;
    private final BidMapper bidMapper;
    private final ImageService imageService;
    private final RatesService ratesService;

    public Page<LotReadDTO> findAll(Integer page, Integer limit, LotFilterDTO filter, LotSortCriteria sort, UUID userId) {
        Sort by = Sort.by(sort.getOrder(), sort.getField().getName());
        PageRequest req = PageRequest.of(page - 1, limit, by);
        Specification<Lot> specification = LotSpecification.getFilterSpecification(filter);
        return lotRepository.findAll(specification, req)
                .map(lot -> map(lot, userId));
    }

    public Page<LotReadDTO> findUsersLotsByStatus(Integer page, Integer limit,LotSortCriteria sort, String status, UUID userId) {
        Sort by = Sort.by(sort.getOrder(), sort.getField().getName());
        PageRequest req = PageRequest.of(page - 1, limit, by);
        List<LotStatus> statuses = Arrays.stream(status.split(","))
                .map(String::trim)
                .map(LotStatus::valueOf)
                .toList();
        return lotRepository.findByUserIdAndStatusIn(userId, statuses, req)
                .map(lot -> map(lot, userId));
    }

    public Page<LotReadDTO> findByStatus(Integer page, Integer limit, LotStatus status, LotSortCriteria sort, UUID userId) {
        Sort by = Sort.by(sort.getOrder(), sort.getField().getName());
        PageRequest req = PageRequest.of(page - 1, limit, by);
        return lotRepository.findByStatus(status, req)
                .map(lot -> map(lot, userId));
    }

    public Optional<LotReadDTO> findById(Long lotId, UUID userId) {
        return lotRepository.findById(lotId)
                .map(lot -> map(lot, userId));
    }

    @Transactional
    public boolean delete(Long id, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {throw new UserNotRegisteredException();});
        return lotRepository.findById(id)
                .map(lot -> {
                    List<Image> images = lot.getImages();
                    for (Image image : images) {
                        imageService.deleteImage(image.getId());
                    }
                    validateLotStatus(lot, MODERATED, CANCELLED, EXPIRED);
                    validatePermissions(lot, userDto);
                    lotRepository.delete(lot);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public LotReadDTO create(LotUpdateDTO dto, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {throw new UserNotRegisteredException();});
        Optional<Category> category = categoryRepository.findById(dto.categoryId());
        validateEntity(category, Category.class);
        return Optional.of(dto)
                .map(lotMapper::toLot)
                .map(lot -> {
                    lot.setBidQuantity(0);
                    lot.setUser(user.get());
                    lot.setStatus(LotStatus.MODERATED);
                    convertPriceToUSD(lot);
                    if (lot.getLengthUnit().equals(LengthUnit.CENTIMETER)) {
                        lot.setFromSize(lot.getFromSize() * 10);
                        lot.setToSize(lot.getToSize() * 10);
                    }
                    return lot;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO)
                .orElseThrow();
    }

    @Transactional
    public Optional<LotReadDTO> update(Long id, LotUpdateDTO dto, UserAuthDto userDto, List<MultipartFile> newImages) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {throw new UserNotRegisteredException();});
        return lotRepository.findById(id)
                .map(lot -> {
                    validatePermissions(lot, userDto);
                    validateLotStatus(lot, MODERATED, CANCELLED);
                    lotMapper.map(lot, imageService.updateListImagesForLot(newImages, lot));
                    lotMapper.map(lot, dto);
                    convertPriceToUSD(lot);
                    if (lot.getLengthUnit().equals(LengthUnit.CENTIMETER)) {
                        lot.setFromSize(lot.getFromSize() * 10);
                        lot.setToSize(lot.getToSize() * 10);
                    }
                    return lot;
                })
                .map(lotRepository::save)
                .map(lot -> map(lot, userDto.id()));
    }

    @Transactional
    public Optional<LotReadDTO> buy(Long lotId, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {throw new UserNotRegisteredException();});
        return lotRepository.findById(lotId)
                .map(lot -> {
                    if (lot.getUser().getId().equals(userDto.id())) {
                        throw new PermissionsDeniedException("You can't make buy your lots", HttpStatus.FORBIDDEN);
                    }
                    validateLotStatus(lot, ACTIVE, AUCTION_ENDED);
                    lot.setStatus(LotStatus.SOLD);
                    bidRepository.findByLotIdAndStatus(lot.getId(), BidStatus.LEADING)
                            .ifPresent(bid -> bid.setStatus(BidStatus.OVERBID));
                    lot.setBuyerId(userDto.id());
                    return lot;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO);
    }

    @Transactional
    public Optional<LotReadDTO> reject(Long id, String message) {
        return lotRepository.findById(id)
                .map(lot -> {
                    validateLotStatus(lot, MODERATED);
                    lot.setStatus(CANCELLED);
                    lot.setRejectMessage(message);
                    return lot;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO);
    }

    @Transactional
    public Optional<LotReadDTO> approve(Long id) {
        return lotRepository.findById(id)
                .map(lot -> {
                    validateLotStatus(lot, MODERATED, CANCELLED);
                    lot.setStatus(LotStatus.ACTIVE);
                    lot.setRejectMessage(null);
                    return  lot;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO);
    }

    @Transactional
    public Optional<LotReadDTO> deactivate(Long id, UserAuthDto user) {
        return lotRepository.findById(id)
                .map(lot -> {
                    validateLotStatus(lot, MODERATED, CANCELLED, EXPIRED);
                    validatePermissions(lot, user);
                    lot.setStatus(LotStatus.DEACTIVATED);
                    lot.setRejectMessage(null);
                    return  lot;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO);
    }

    @Transactional
    public Optional<LotReadDTO> confirm(Long id, UserAuthDto user) {
        return lotRepository.findById(id)
                .map(lot -> {
                    validateLotStatus(lot, ACTIVE);
                    validatePermissions(lot, user);
                    Optional<Bid> bidWrapper = bidRepository.findByLotIdAndStatus(lot.getId(), BidStatus.LEADING);
                    validateEntity(bidWrapper, Bid.class);
                    bidWrapper.get().setStatus(BidStatus.WON);
                    lot.setStatus(LotStatus.SOLD);
                    lot.setRejectMessage(null);
                    return  lot;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO);
    }

    public Page<LotReadDTO> findBought(Integer page, Integer limit, UserAuthDto user) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return lotRepository.findByBuyerIdAndStatus(user.id(), LotStatus.SOLD, req)
                .map(lot -> map(lot, user.id()));
    }

    public Page<LotReadDTO> findBidsByUserId(UUID id, Integer page, Integer limit, BidStatus status) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return lotRepository.findAllByBidStatus(status, id, req)
                .map(lot -> map(lot, id));
    }

    private void validateLotStatus(Lot lot, LotStatus... statuses) {
        boolean statusIsValid = Arrays.stream(statuses).anyMatch(x -> x.equals(lot.getStatus()));
        if (!statusIsValid) {
            throw new EntityStatusViolationException(
                    "Lot might be in another state to proceed this operation",
                    BAD_REQUEST
            );
        }
    }

    private void convertPriceToUSD(Lot lot) {
        double total = ratesService.convertToUSD(lot.getTotalPrice(), lot.getCurrency());
        double start = ratesService.convertToUSD(lot.getStartPrice(), lot.getCurrency());
        lot.setTotalPrice(total);
        lot.setStartPrice(start);
    }

    private void convertPriceFromUSD(LotReadDTO dto, Lot lot) {
        Currency preferred = lot.getUser().getCurrency();
        double total = ratesService.convertFromUSD(lot.getTotalPrice(), preferred);
        double start = ratesService.convertFromUSD(lot.getStartPrice(), preferred);
        dto.setTotalPrice(Math.floor(total * 100) / 100);
        dto.setStartPrice(Math.floor(start * 100) / 100);
        dto.setCurrency(preferred);
    }

    private LotReadDTO map(Lot lot, UUID userId) {
        LotReadDTO lotReadDTO = lotMapper.toLotReadDTO(lot);
        BidReadDto leading = bidMapper.toReadDto(bidRepository.findByLotIdAndStatus(lot.getId(), BidStatus.LEADING).orElse(null));
        Optional<Bid> bid = Optional.empty();
        if (userId != null) {
            bid = bidRepository.findByUserIdAndLotId(userId, lot.getId());
        }
        BidReadDto users = bidMapper.toReadDto(bid.orElse(null));
        lotReadDTO.setLeading(leading);
        lotReadDTO.setUsers(users);
        convertPriceFromUSD(lotReadDTO, lot);
        if (lot.getLengthUnit().equals(LengthUnit.CENTIMETER)) {
            lotReadDTO.setFromSize(lot.getFromSize() / 10);
            lotReadDTO.setToSize(lot.getToSize() / 10);
        }
        return lotReadDTO;
    }
}
