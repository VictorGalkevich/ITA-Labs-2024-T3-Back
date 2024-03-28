package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.UUID;

public interface LotRepository extends
        JpaRepository<Lot, Long>,
        JpaSpecificationExecutor<Lot> {
    Page<Lot> findAllByCategoryId(Integer id, Pageable pageable);

    Page<Lot> findByStatus(LotStatus status, Pageable pageable);

    Page<Lot> findByStatusAndUserId(LotStatus lotStatus, UUID userId, Pageable pageable);

    @Modifying
    @Query(
            value = """
                    UPDATE Lot l
                    SET l.status = CASE
                                     WHEN l.bidQuantity = 0 THEN 'EXPIRED'
                                     WHEN l.bidQuantity > 0 THEN 'SOLD'
                    ELSE l.status
                    END
                    WHERE l.expirationDate < ?1
                    """
    )
    int updateExpiredLots(Instant time);

    @Query("SELECT l FROM Bid b JOIN Lot l ON l.id = b.lotId WHERE b.status =?1 AND b.userId=?2")
    Page<Lot> findAllByBidStatus(BidStatus status, UUID uuid, Pageable pageable);
}