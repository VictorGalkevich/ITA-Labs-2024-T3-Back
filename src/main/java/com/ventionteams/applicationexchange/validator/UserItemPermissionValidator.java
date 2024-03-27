package com.ventionteams.applicationexchange.validator;

import com.ventionteams.applicationexchange.annotation.Validator;
import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
import com.ventionteams.applicationexchange.entity.UserMappedEntity;
import com.ventionteams.applicationexchange.exception.AccessValidationException;
import org.springframework.http.HttpStatus;

@Validator
public class UserItemPermissionValidator {
   public <T extends UserMappedEntity> void validate(T entity, UserAuthDto user) {
       if (!entity.getUser().getId().equals(user.id())) {
           throw new AccessValidationException(
                   "Only owner of this item can access it",
                   HttpStatus.FORBIDDEN
           );
       }
    }
}
