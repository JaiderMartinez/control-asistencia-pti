package com.colegio.asistencia.mapper.request;

import com.colegio.asistencia.dto.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.entity.EnvironmentPtiEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IEnvironmentRequestMapper {

    @Mapping(target = "codePti", ignore = true)
    @Mapping(target = "userEntity", ignore = true)
    EnvironmentPtiEntity toEnvironmentPtiEntity(CreateEnvironmentPtiRequestDto createEnvironmentPtiRequestDto);
}
