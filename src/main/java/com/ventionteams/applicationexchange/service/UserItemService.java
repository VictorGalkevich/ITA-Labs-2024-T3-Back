package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
import com.ventionteams.applicationexchange.entity.UserMappedEntity;
import com.ventionteams.applicationexchange.exception.AccessValidationException;
import org.springframework.http.HttpStatus;

public abstract class UserItemService extends EntityRelatedService {
    public static <T extends UserMappedEntity> void validatePermissions(T entity, UserAuthDto user) {
        if (!entity.getUser().getId().equals(user.id())) {
            throw new AccessValidationException(
                    "Only owner of this item can access it",
                    HttpStatus.FORBIDDEN
            );
        }
    }
}
