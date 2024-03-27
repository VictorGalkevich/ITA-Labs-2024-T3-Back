package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.dto.read.BidForUserDto;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserBidRepositoryImpl implements UserBidRepository {
    private final JdbcClient client;
    private static final String FIND_ALL_BY_USER_ID_AND_STATUS = """
            SELECT bids.id, amount, lot_id, bids.status,
                         amount, bids.currency, l.title,
                         l.expiration_date, l.total_price,
                         l.price_per_unit, l.weight
                  FROM bids
                  INNER JOIN public.lots l
                  ON l.id = bids.lot_id
                  WHERE bids.status=?
                  AND bids.user_id=?
                  OFFSET ?
                  FETCH FIRST ? ROWS ONLY
            """;

    @Override
    public Page<BidForUserDto> findAllByUserIdAndStatus(UUID id, PageRequest req, BidStatus status) {
        List<BidForUserDto> list = client.sql(FIND_ALL_BY_USER_ID_AND_STATUS)
                .params(
                        status.toString(),
                        id,
                        req.getOffset(),
                        req.getPageSize()
                )
                .query((rs, rn) -> new BidForUserDto(
                        rs.getLong("id"),
                        rs.getLong("lot_id"),
                        BidStatus.valueOf(rs.getString("status")),
                        rs.getLong("amount"),
                        Currency.valueOf(rs.getString("currency")),
                        rs.getString("title"),
                        rs.getTimestamp("expiration_date").toInstant(),
                        rs.getDouble("total_price"),
                        rs.getDouble("price_per_unit"),
                        Weight.valueOf(rs.getString("weight"))
                ))
                .list();
        return new PageImpl<>(list, req, list.size());
    }
}
