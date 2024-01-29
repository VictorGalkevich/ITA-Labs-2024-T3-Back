package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.dto.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.UserReadDto;
import com.ventionteams.applicationexchange.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserReadDto toUserReadDto(User user);

    User toUser(UserCreateEditDto dto);
}
