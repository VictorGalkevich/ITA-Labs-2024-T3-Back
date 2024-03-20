package com.ventionteams.applicationexchange.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LotUpdateRepositoryImpl implements LotUpdateRepository {
    private final JdbcClient client;
    private static final String UPDATE_EXPIRED_LINKS = """
            UPDATE lots
            SET status = CASE
                             WHEN bid_quantity = 0 THEN 'EXPIRED'
                             WHEN bid_quantity > 0 THEN 'SOLD'
            ELSE status
            END
            WHERE expiration_date < NOW();
            """;


    public int updateExpiredLots() {
        return client.sql(UPDATE_EXPIRED_LINKS)
                .update();
    }
}
