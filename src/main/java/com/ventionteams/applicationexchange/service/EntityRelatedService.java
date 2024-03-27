package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.validator.MissingEntityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class EntityRelatedService {
    protected MissingEntityValidator entityValidator;

    @Autowired
    public final void setEntityValidator(MissingEntityValidator entityValidator) {
        this.entityValidator = entityValidator;
    }
}
