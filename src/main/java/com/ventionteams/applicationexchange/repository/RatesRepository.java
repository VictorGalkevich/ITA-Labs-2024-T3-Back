package com.ventionteams.applicationexchange.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RatesRepository {
    private final JdbcClient client;

    public void updateRates(Double buyByn, Double sellByn, Double buyEur, Double sellEur) {
        String updateQuery = "UPDATE rates SET buy_byn = ?, sell_byn = ?, buy_eur = ?, sell_eur = ?";
        client.sql(updateQuery)
                .params(buyByn,
                        sellByn,
                        buyEur,
                        sellEur)
                .update();
    }

    public Map<String, Object> getRates() {
        String selectQuery = "SELECT * FROM rates";
        return client.sql(selectQuery)
                .query().singleRow();
    }
}
