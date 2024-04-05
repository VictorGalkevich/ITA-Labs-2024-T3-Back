package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.PurchaseRequest;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.UUID;

public interface RequestRepository extends JpaRepository<PurchaseRequest, Long> {
    Page<PurchaseRequest> findByStatus(LotStatus status, Pageable pageable);

    Page<PurchaseRequest> findAllByUserIdAndStatus(UUID uuid, LotStatus status, Pageable pageable);

    @Modifying
    @Query(
            value = """
                    UPDATE PurchaseRequest r
                    SET r.status = CASE
                                     WHEN r.offerQuantity = 0 THEN 'EXPIRED'
                                     WHEN r.offerQuantity > 0 THEN 'SOLD'
                    ELSE r.status
                    END
                    WHERE r.expirationDate < ?1
                    """
    )
    int updateExpiredLots(Instant time);
}
