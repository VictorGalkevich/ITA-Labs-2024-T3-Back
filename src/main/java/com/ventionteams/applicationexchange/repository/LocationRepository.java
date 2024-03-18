package com.ventionteams.applicationexchange.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LocationRepository {
    private final JdbcClient client;
    private static final String FIND_ALL_COUNTRIES = "SELECT country FROM locations GROUP BY country";
    private static final String FIND_ALL_CITIES_FOR_COUNTRY = "SELECT region FROM locations WHERE country=?";

    public List<String> findAllCountries() {
        return client.sql(FIND_ALL_COUNTRIES)
                .query((rs, rn) -> rs.getString("country"))
                .list();
    }

    public List<String> findAllCitiesForCountry(String country) {
        return client.sql(FIND_ALL_CITIES_FOR_COUNTRY)
                .params(country)
                .query((rs, rn) -> rs.getString("region"))
                .list();
    }
}
