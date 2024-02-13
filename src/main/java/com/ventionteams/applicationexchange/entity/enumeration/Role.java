package com.ventionteams.applicationexchange.entity.enumeration;

import org.springframework.security.core.GrantedAuthority;
import com.ventionteams.applicationexchange.entity.ResourceContainer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements ResourceContainer, GrantedAuthority {
    EXCHANGE_EMPLOYEE("exchange employee"),
    SYSTEM_ADMINISTRATOR("system administrator"),
    REGISTERED_USER("registered user");

    private String name;
  
    @Override
    public String getAuthority() {
        return name();
    }
}
