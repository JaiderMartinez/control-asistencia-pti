package com.colegio.asistencia.mapper;

import com.colegio.asistencia.dto.response.EnvironmentOfPTIResponseDto;
import com.colegio.asistencia.persistence.jpa.entity.EnvironmentPtiEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TeacherMapper {

    public EnvironmentOfPTIResponseDto mapEnvironmentToEnvironmentOfPTIResponseDto(EnvironmentPtiEntity environmentPtiEntity) {
        return EnvironmentOfPTIResponseDto.builder()
                .codePti(environmentPtiEntity.getCodePti())
                .name(environmentPtiEntity.getName())
                .build();
    }
}
