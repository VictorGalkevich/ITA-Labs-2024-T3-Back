package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.validator.UserItemPermissionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public abstract class UserItemService extends EntityRelatedService {
    protected UserItemPermissionValidator permissionValidator;

    @Autowired
    public final void setPermissionValidator(UserItemPermissionValidator permissionValidator) {
        this.permissionValidator = permissionValidator;
    }
}
