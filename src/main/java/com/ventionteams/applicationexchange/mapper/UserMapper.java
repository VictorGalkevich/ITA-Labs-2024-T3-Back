package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.create.UserCreateEditDto;
import com.ventionteams.applicationexchange.dto.read.UserReadDto;
import com.ventionteams.applicationexchange.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class, uses = BidMapper.class)
public interface UserMapper {
    UserReadDto toUserReadDto(User user);

    User toUser(UserCreateEditDto dto);

    void map(@MappingTarget User to, UserCreateEditDto from);
}
