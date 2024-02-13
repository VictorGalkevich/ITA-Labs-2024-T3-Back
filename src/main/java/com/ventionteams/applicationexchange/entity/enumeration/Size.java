package com.ventionteams.applicationexchange.entity.enumeration;

import com.ventionteams.applicationexchange.entity.ResourceContainer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Size implements ResourceContainer {
    LARGE("Large"),
    SMALL("Small"),
    MEDIUM("Medium");

    private String name;
}
