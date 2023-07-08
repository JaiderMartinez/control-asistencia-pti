package com.colegio.asistencia.mapper.response;

import com.colegio.asistencia.dto.response.EnvironmentOfPTIResponseDto;
import com.colegio.asistencia.persistence.jpa.entity.EnvironmentPtiEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IEnvironmentPtiResponseMapper {

    EnvironmentOfPTIResponseDto toEnvironmentsOfPTIResponseDto(EnvironmentPtiEntity environmentPtiEntity);
}
