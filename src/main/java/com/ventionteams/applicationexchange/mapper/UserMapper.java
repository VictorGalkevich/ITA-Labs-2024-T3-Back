package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.dto.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.UserReadDto;
import com.ventionteams.applicationexchange.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserReadDto toUserReadDto(User user);

    @Mapping(target = "id", ignore = true)
    User toUser(UserCreateEditDto dto);
}
