package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.read.BidForUserDto;
import com.ventionteams.applicationexchange.dto.read.BidReadDto;
import com.ventionteams.applicationexchange.dto.create.LotFilterDTO;
import com.ventionteams.applicationexchange.dto.read.LotReadDTO;
import com.ventionteams.applicationexchange.dto.create.LotUpdateDTO;
import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.LotSortCriteria;
import com.ventionteams.applicationexchange.entity.User;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.exception.AuctionEndedException;
import com.ventionteams.applicationexchange.exception.UserNotRegisteredException;
import com.ventionteams.applicationexchange.mapper.BidMapper;
import com.ventionteams.applicationexchange.mapper.CategoryMapper;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.ventionteams.applicationexchange.entity.enumeration.LotStatus.AUCTION_ENDED;
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

    public Page<LotReadDTO> findAll(Integer page, Integer limit, LotFilterDTO filter, LotSortCriteria sort, UUID userId) {
        Sort by = Sort.by(sort.getOrder(), sort.getField().getName());
        PageRequest req = PageRequest.of(page - 1, limit, by);
        Specification<Lot> specification = LotSpecification.getFilterSpecification(filter);
        return lotRepository.findAll(specification, req)
                .map(lot -> map(lot, userId));
    }

    public Page<LotReadDTO> findUsersLotsByStatus(Integer page, Integer limit, LotStatus status, LotSortCriteria sort, UUID userId) {
        Sort by = Sort.by(sort.getOrder(), sort.getField().getName());
        PageRequest req = PageRequest.of(page - 1, limit, by);
        return lotRepository.findByStatusAndUserId(status, userId, req)
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
        entityValidator.validate(user, () -> {throw new UserNotRegisteredException();});
        return lotRepository.findById(id)
                .map(lot -> {
                    permissionValidator.validate(lot, userDto);
                    lotRepository.delete(lot);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public LotReadDTO create(LotUpdateDTO dto, UserAuthDto userDto) {
            Optional<User> user = userRepository.findById(userDto.id());
            entityValidator.validate(user, () -> {throw new UserNotRegisteredException();});
            Optional<Category> category = categoryRepository.findById(dto.categoryId());
            entityValidator.validate(category, Category.class);
            return Optional.of(dto)
                    .map(lotMapper::toLot)
                    .map(x -> {
                        x.setBidQuantity(0);
                        x.setUser(user.get());
                        x.setStatus(LotStatus.MODERATED);
                        return x;
                    })
                    .map(lotRepository::save)
                    .map(lotMapper::toLotReadDTO)
                    .orElseThrow();
    }

    @Transactional
    public Optional<LotReadDTO> update(Long id, LotUpdateDTO dto, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        entityValidator.validate(user, () -> {throw new UserNotRegisteredException();});
        return lotRepository.findById(id)
                .map(lot -> {
                    permissionValidator.validate(lot, userDto);
                    lotMapper.map(lot, dto);
                    return lot;
                })
                .map(lotRepository::save)
                .map(lot -> map(lot, userDto.id()));
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
        return lotReadDTO;
    }

    public Optional<LotReadDTO> buy(Long lotId, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        entityValidator.validate(user, () -> {throw new UserNotRegisteredException();});
        return lotRepository.findById(lotId)
                .map(lot -> {
                    if (!(lot.getStatus().equals(LotStatus.ACTIVE)
                          || lot.getStatus().equals(AUCTION_ENDED))) {
                        throw new AuctionEndedException("This lot can't be sold, please check it's status",
                                BAD_REQUEST);
                    }
                    lot.setStatus(LotStatus.SOLD);
                    return lot;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO);
    }

    public Optional<LotReadDTO> reject(Long id, String message) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lot.setStatus(LotStatus.CANCELLED);
                    lot.setRejectMessage(message);
                    return  lot;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO);
    }

    public Optional<LotReadDTO> approve(Long id) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lot.setStatus(LotStatus.ACTIVE);
                    lot.setRejectMessage(null);
                    return  lot;
                })
                .map(lotRepository::save)
                .map(lotMapper::toLotReadDTO);
    }

    public Page<LotReadDTO> findBidsByUserId(UUID id, Integer page, Integer limit, BidStatus status) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return lotRepository.findAllByBidStatus(status, id, req)
                .map(lotMapper::toLotReadDTO);
    }
}
