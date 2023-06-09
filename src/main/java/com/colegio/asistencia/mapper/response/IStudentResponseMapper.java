package com.colegio.asistencia.mapper.response;

import com.colegio.asistencia.dto.response.SearchFoundStudentResponseDto;
import com.colegio.asistencia.persistence.jpa.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IStudentResponseMapper {

    SearchFoundStudentResponseDto toSearchFoundStudentResponseDto(StudentEntity studentEntity);
}
