package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.repository.LocationRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@TransactionalService
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository repository;

    public List<String> findAllCountries() {
        return repository.findAllCountries();
    }

    public List<String> findAllCitiesForCountry(String country) {
        return repository.findAllCitiesForCountry(country);
    }
}
