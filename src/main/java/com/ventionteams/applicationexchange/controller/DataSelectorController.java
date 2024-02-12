package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.entity.enumeration.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data-selection")
@RequiredArgsConstructor
public class DataSelectorController {

    @GetMapping
    public Map<String, List<String>> getData() {
        Map<String, List<String>> data = new HashMap<>();
        data.put("packaging", Arrays.stream(Packaging.values())
                .map(Packaging::getName)
                .collect(Collectors.toList()));
        data.put("weight", Arrays.stream(Weight.values())
                .map(Weight::getName)
                .collect(Collectors.toList()));
        data.put("size", Arrays.stream(Size.values())
                .map(Size::getName)
                .collect(Collectors.toList()));
        data.put("role", Arrays.stream(Role.values())
                .map(Role::getName)
                .collect(Collectors.toList()));
        data.put("status", Arrays.stream(Status.values())
                .map(Status::getName)
                .collect(Collectors.toList()));
        data.put("currency", Arrays.stream(Currency.values())
                .map(Currency::getName)
                .collect(Collectors.toList()));

        return data;
    }
}
