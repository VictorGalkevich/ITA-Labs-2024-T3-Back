package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.create.BidCreateDto;
import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
import com.ventionteams.applicationexchange.dto.read.BidReadDto;
import com.ventionteams.applicationexchange.entity.Bid;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.User;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.exception.AuctionEndedException;
import com.ventionteams.applicationexchange.exception.IllegalPriceException;
import com.ventionteams.applicationexchange.exception.PermissionsDeniedException;
import com.ventionteams.applicationexchange.exception.UserNotRegisteredException;
import com.ventionteams.applicationexchange.mapper.BidMapper;
import com.ventionteams.applicationexchange.repository.BidRepository;
import com.ventionteams.applicationexchange.repository.LotRepository;
import com.ventionteams.applicationexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.ventionteams.applicationexchange.entity.enumeration.BidStatus.LEADING;
import static com.ventionteams.applicationexchange.entity.enumeration.BidStatus.OVERBID;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@TransactionalService
@RequiredArgsConstructor
public class BidService extends EntityRelatedService {
    private final BidRepository bidRepository;
    private final LotRepository lotRepository;
    private final UserRepository userRepository;
    private final BidMapper bidMapper;
    private final RatesService ratesService;

    public Page<BidReadDto> findAll(Integer page, Integer limit) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return bidRepository.findAll(req)
                .map(bidMapper::toReadDto);
    }

    public Optional<BidReadDto> findById(Long id) {
        return bidRepository.findById(id)
                .map(bidMapper::toReadDto);
    }

    @Transactional
    public BidReadDto create(BidCreateDto dto, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {
            throw new UserNotRegisteredException();
        });
        return Optional.of(dto)
                .map(bidMapper::toBid)
                .map(bid -> {
                    setOverbidForLot(bid, userDto);
                    deleteOldBidFromUser(bid);
                    return bidRepository.save(bid);
                })
                .map(bidMapper::toReadDto)
                .orElseThrow();
    }

    private void setOverbidForLot(Bid bid, UserAuthDto userDto) {
        Long lotId = bid.getLotId();
        Optional<Lot> lotWrapper = lotRepository.findById(lotId);
        validateEntity(lotWrapper, Lot.class);

        Lot lot = lotWrapper.get();

        if (lot.getUser().getId().equals(userDto.id())) {
            throw new PermissionsDeniedException("You can't make bids at yout lots", HttpStatus.FORBIDDEN);
        }

        if (bidsRestricted(lot)) {
            throw new AuctionEndedException("No more bids allowed, max bid has already been done",
                    BAD_REQUEST);
        }

        lot.setBidQuantity(lot.getBidQuantity() + 1);
        double bidAmount = ratesService.convertToUSD(bid.getAmount(), bid.getCurrency());
        bid.setAmount(bidAmount);
        bidRepository.findByLotIdAndStatus(lotId, LEADING)
                .ifPresentOrElse(prevBid -> {
                    double startPrice = lot.getStartPrice();
                    double totalPrice = lot.getTotalPrice();

                    if (startPrice <= bidAmount && bidAmount <= totalPrice - 1) {
                        prevBid.setStatus(OVERBID);
                        if (bidAmount >= totalPrice - 1) {
                            lot.setStatus(LotStatus.AUCTION_ENDED);
                        }
                        lot.setStartPrice(bidAmount + 1);
                    } else {
                        String msg = "Price %s is less than current start price (%s)";
                        double val = startPrice;
                        if (bidAmount > totalPrice - 1) {
                            msg = "Price %s is bigger than current max price (%s)";
                            val = totalPrice - 1;
                        }
                        throw new IllegalPriceException(
                                String.format(msg, bidAmount, val),
                                BAD_REQUEST
                        );
                    }
                }, () -> lot.setStartPrice(bidAmount + 1));
        lot.setBuyerId(userDto.id());
    }

    private void deleteOldBidFromUser(Bid bid) {
        UUID userId = bid.getUserId();
        Long lotId = bid.getLotId();
        Optional<Bid> byUserId = bidRepository.findByUserIdAndLotId(userId, lotId);
        byUserId.ifPresent(bidRepository::delete);
    }

    private boolean bidsRestricted(Lot lot) {
        return !lot.getStatus().equals(LotStatus.ACTIVE) ||
               lot.getExpirationDate().isBefore(Instant.now());
    }
}


