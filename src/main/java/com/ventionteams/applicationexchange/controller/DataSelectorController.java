package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.entity.ResourceContainer;
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

    @GetMapping
    public DataSelection getData() {
        return new DataSelection(getList(Packaging.class), getList(Weight.class),
                getList(Size.class), getList(Role.class), getList(Status.class), getList(Currency.class));
    }

    public static <T extends Enum<T> & ResourceContainer> List<String> getList(Class<T> enumeration) {
        return Arrays.stream(enumeration.getEnumConstants())
                .map(ResourceContainer::getName)
                .collect(Collectors.toList());
    }

    public record DataSelection(
            List<String> packaging,
            List<String> weight,
            List<String> size,
            List<String> role,
            List<String> status,
            List<String> currency
    ){}
}
