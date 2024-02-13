package com.ventionteams.applicationexchange.entity.enumeration;

import com.ventionteams.applicationexchange.entity.ResourceContainer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status implements ResourceContainer {
    ACTIVE("active"),
    COMPLETED("completed"),
    MODERATED("moderated");

    private String name;
}
