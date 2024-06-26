package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.create.OfferCreateEditDto;
import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
import com.ventionteams.applicationexchange.dto.read.OfferReadDto;
import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.PurchaseRequest;
import com.ventionteams.applicationexchange.entity.User;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.entity.enumeration.OfferStatus;
import com.ventionteams.applicationexchange.exception.AuctionEndedException;
import com.ventionteams.applicationexchange.exception.PermissionsDeniedException;
import com.ventionteams.applicationexchange.exception.UserNotRegisteredException;
import com.ventionteams.applicationexchange.mapper.OfferMapper;
import com.ventionteams.applicationexchange.repository.BidRepository;
import com.ventionteams.applicationexchange.repository.LotRepository;
import com.ventionteams.applicationexchange.repository.OfferRepository;
import com.ventionteams.applicationexchange.repository.RequestRepository;
import com.ventionteams.applicationexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.ventionteams.applicationexchange.entity.enumeration.LotStatus.AUCTION_ENDED;
import static com.ventionteams.applicationexchange.entity.enumeration.LotStatus.SOLD;
import static com.ventionteams.applicationexchange.entity.enumeration.OfferStatus.WON;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@TransactionalService
@RequiredArgsConstructor
public class OfferService extends UserItemService {
    private final OfferRepository offerRepository;
    private final LotRepository lotRepository;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final BidRepository bidRepository;
    private final OfferMapper offerMapper;

    public Page<OfferReadDto> findAll(Integer page, Integer limit) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return offerRepository.findAll(req)
                .map(offerMapper::toReadDto);
    }

    public Optional<OfferReadDto> findById(Long id) {
        return offerRepository.findById(id)
                .map(offerMapper::toReadDto);
    }

    @Transactional
    public OfferReadDto create(OfferCreateEditDto dto, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {
            throw new UserNotRegisteredException();
        });
        Optional<Lot> lot = lotRepository.findById(dto.lotId());
        Optional<PurchaseRequest> req = requestRepository.findById(dto.purchaseRequestId());
        validateLot(lot);
        validateRequest(req);
        validatePermissions(lot.get(), userDto);
        if (lot.get().getUser().getId().equals(req.get().getUser().getId())) {
            throw new PermissionsDeniedException(
                    "U cant create offers at your lots",
                    BAD_REQUEST
            );
        }
        return Optional.of(dto)
                .map(offerMapper::toOffer)
                .map(offer -> {
                    PurchaseRequest purchaseRequest = req.get();
                    purchaseRequest.setOfferQuantity(purchaseRequest.getOfferQuantity() + 1);
                    offer.setStatus(OfferStatus.PENDING);
                    offer.setLot(lot.get());
                    return offer;
                })
                .map(offerRepository::save)
                .map(offerMapper::toReadDto)
                .orElseThrow();
    }

    private void validateLot(Optional<Lot> lot) {
        validateEntity(lot, Lot.class);
        if (!(lot.get().getStatus().equals(LotStatus.ACTIVE)
              || lot.get().getStatus().equals(AUCTION_ENDED))) {
            throw new AuctionEndedException("This lot can't be sold, please check it's status",
                    BAD_REQUEST);
        }
    }

    private void validateRequest(Optional<PurchaseRequest> request) {
        validateEntity(request, PurchaseRequest.class);
        if (!request.get().getStatus().equals(LotStatus.ACTIVE)) {
            throw new AuctionEndedException("No more offers allowed. The owner either has closed the topic or found the item",
                    BAD_REQUEST);
        }
    }

    @Transactional
    public Optional<OfferReadDto> decide(Long id, UserAuthDto userDto, OfferStatus status) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {
            throw new UserNotRegisteredException();
        });

        return offerRepository.findById(id)
                .map(offer -> {
                    Optional<PurchaseRequest> req = requestRepository.findById(offer.getPurchaseRequestId());
                    validateEntity(req, PurchaseRequest.class);
                    validatePermissions(req.get(), userDto);
                    offer.setStatus(status);
                    return offer;
                })
                .map(offerRepository::save)
                .map(offerMapper::toReadDto);
    }

    @Transactional
    public Optional<OfferReadDto> sell(Long id, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {
            throw new UserNotRegisteredException();
        });
        return offerRepository.findById(id)
                .map(offer -> {
                    if (!offer.getStatus().equals(OfferStatus.READY_TO_BUY)) {
                        throw new PermissionsDeniedException(
                                "You cant sell this lot",
                                BAD_REQUEST
                        );
                    }
                    Lot lot = offer.getLot();
                    validatePermissions(lot, userDto);
                    Optional<Bid> leadingBid = bidRepository.findByLotIdAndStatus(lot.getId(), BidStatus.LEADING);
                    leadingBid.ifPresent(it -> it.setStatus(BidStatus.OVERBID));
                    lot.setStatus(SOLD);
                    offer.setStatus(WON);
                    return offer;
                })
                .map(offerRepository::save)
                .map(offerMapper::toReadDto);
    }

    @Transactional
    public boolean delete(Long id, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {
            throw new UserNotRegisteredException();
        });
        return offerRepository.findById(id)
                .map(offer -> {
                    validatePermissions(offer.getLot(), userDto);
                    PurchaseRequest purchaseRequest = requestRepository.findById(offer.getPurchaseRequestId()).get();
                    purchaseRequest.setOfferQuantity(purchaseRequest.getOfferQuantity() - 1);
                    offerRepository.delete(offer);
                    return true;
                })
                .orElse(false);
    }

    public Page<OfferReadDto> findOffersByUserId(UUID id, Integer page, Integer limit, OfferStatus status) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return offerRepository.findByUserId(id, req)
                .map(offerMapper::toReadDto);
    }
}
