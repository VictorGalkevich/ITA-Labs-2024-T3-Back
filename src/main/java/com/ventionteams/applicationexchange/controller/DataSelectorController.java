package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.entity.enumeration.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data-selection")
@RequiredArgsConstructor
public class DataSelectorController {

    @GetMapping
    public DataSelection getData() {
        return new DataSelection(getList(Packaging.class), getList(Weight.class),
                getList(LengthUnit.class), getList(Role.class), getList(LotStatus.class), getList(Currency.class));
    }

    public static <T extends Enum<T>> T[] getList(Class<T> enumeration) {
        return enumeration.getEnumConstants();
    }

    public record DataSelection(
            Packaging[] packaging,
            Weight[] weight,
            LengthUnit[] lengthUnits,
            Role[] role,
            LotStatus[] status,
            Currency[] currency
    ) {
    }
}
