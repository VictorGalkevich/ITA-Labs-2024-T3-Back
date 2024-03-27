package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.BidReadDto;
import com.ventionteams.applicationexchange.dto.LotFilterDTO;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.dto.UserAuthDto;
import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.LotSortCriteria;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.exception.UserNotRegisteredException;
import com.ventionteams.applicationexchange.mapper.BidMapper;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.BidRepository;
import com.ventionteams.applicationexchange.repository.LotRepository;
import com.ventionteams.applicationexchange.specification.LotSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@TransactionalService
@RequiredArgsConstructor
public class LotService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;
    private final LotMapper lotMapper;
    private final BidMapper bidMapper;
    private final ImageService imageService;

    public Page<LotReadDTO> findAll(Integer page, Integer limit, LotFilterDTO filter, LotSortCriteria sort, UUID userId) {
        Sort by = Sort.by(sort.getOrder(), sort.getField().getName());
        PageRequest req = PageRequest.of(page - 1, limit, by);
        Specification<Lot> specification = LotSpecification.getFilterSpecification(filter);
        return lotRepository.findAll(specification, req)
                .map(lot -> map(lot, userId));
    }

    public Optional<LotReadDTO> findById(Long lotId, UUID userId) {
        return lotRepository.findById(lotId)
                .map(lot -> map(lot, userId));
    }

    @Transactional
    public boolean delete(Long id) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lotRepository.delete(lot);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public LotReadDTO create(LotUpdateDTO dto, UserAuthDto user) {
        try {
            return Optional.of(dto)
                    .map(lotMapper::toLot)
                    .map(x -> {
                        x.setBidQuantity(0);
                        x.setUserId(user.id());
                        x.setStatus(LotStatus.MODERATED);
                        return x;
                    })
                    .map(lotRepository::save)
                    .map(lotMapper::toLotReadDTO)
                    .orElseThrow();
        } catch (DataIntegrityViolationException e) {
            throw new UserNotRegisteredException(
                    "You haven't completed the onboarding yet",
                    HttpStatus.FORBIDDEN
            );
        }
    }

    @Transactional
    public Optional<LotReadDTO> update(Long id, LotUpdateDTO dto, UUID userId, List<MultipartFile> newImages) {
        return lotRepository.findById(id)
                .map(lot -> {
                    lotMapper.map(lot, dto);
                    lotMapper.map(lot, imageService.updateListImagesForLot(newImages, lot));
                    return lot;
                })
                .map(lotRepository::save)
                .map(lot -> map(lot, userId));
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
}
