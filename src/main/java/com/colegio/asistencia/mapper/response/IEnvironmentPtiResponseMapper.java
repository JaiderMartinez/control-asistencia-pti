package com.colegio.asistencia.mapper.response;

import com.colegio.asistencia.dto.response.EnvironmentsOfPTIResponseDto;
import com.colegio.asistencia.entity.EnvironmentPtiEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IEnvironmentPtiResponseMapper {

    EnvironmentsOfPTIResponseDto toEnvironmentsOfPTIResponseDto(EnvironmentPtiEntity environmentPtiEntity);
}
