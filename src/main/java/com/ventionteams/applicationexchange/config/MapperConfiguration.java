package com.ventionteams.applicationexchange.config;

import org.mapstruct.*;

@MapperConfig(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MapperConfiguration {
}
