package com.colegio.asistencia.utils.mapper;

import com.colegio.asistencia.dtos.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.dtos.response.EnvironmentOfPTIResponseDto;
import com.colegio.asistencia.models.entity.EnvironmentPtiEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnvironmentMapper {

    public static EnvironmentPtiEntity mapcreateEnvironmentPtiRequestDtotoEnvironmentPtiEntity(CreateEnvironmentPtiRequestDto createEnvironmentPtiRequestDto) {
        return EnvironmentPtiEntity.builder()
                .name(createEnvironmentPtiRequestDto.getName())
                .description(createEnvironmentPtiRequestDto.getDescription())
                .classroom(createEnvironmentPtiRequestDto.getClassroom())
                .build();
    }

    public EnvironmentOfPTIResponseDto mapEnvironmentToEnvironmentOfPTIResponseDto(EnvironmentPtiEntity environmentPtiEntity) {
        return EnvironmentOfPTIResponseDto.builder()
                .codePti(environmentPtiEntity.getCodePti())
                .name(environmentPtiEntity.getName())
                .build();
    }

    public static EnvironmentOfPTIResponseDto toEnvironmentsOfPTIResponseDto(EnvironmentPtiEntity environmentPtiEntity) {
        return EnvironmentOfPTIResponseDto.builder()
                .codePti(environmentPtiEntity.getCodePti())
                .name(environmentPtiEntity.getName())
                .build();
    }
}
