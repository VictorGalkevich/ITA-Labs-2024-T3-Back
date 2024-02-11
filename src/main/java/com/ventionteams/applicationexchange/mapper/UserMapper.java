package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.UserReadDto;
import com.ventionteams.applicationexchange.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mapstruct.InjectionStrategy.*;

@Mapper(config = MapperConfiguration.class, uses = {PasswordEncoder.class}, injectionStrategy = CONSTRUCTOR)
public interface UserMapper {
    UserReadDto toUserReadDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.password()))")
    User toUser(UserCreateEditDto dto);
}
