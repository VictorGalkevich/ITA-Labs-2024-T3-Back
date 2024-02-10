package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.entity.enumeration.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data-selection")
@RequiredArgsConstructor
public class DataSelectorController {

    @GetMapping("/packaging")
    public List<String> getPackagingTypes() {
        return Arrays.stream(Packaging.values())
                .map(Packaging::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/weight")
    public List<String> getWeightMeasurements() {
        return  Arrays.stream(Weight.values())
                .map(Weight::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/size")
    public List<String> getSizes() {
        return  Arrays.stream(Size.values())
                .map(Size::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/role")
    public List<String> getRoles() {
        return  Arrays.stream(Role.values())
                .map(Role::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/status")
    public List<String> getStatuses() {
        return  Arrays.stream(Status.values())
                .map(Status::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/currency")
    public List<String> getCurrency() {
        return  Arrays.stream(Currency.values())
                .map(Currency::getName)
                .collect(Collectors.toList());
    }
}
