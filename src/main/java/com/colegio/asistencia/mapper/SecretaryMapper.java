package com.colegio.asistencia.mapper;

import com.colegio.asistencia.dto.request.StudentRequestDto;
import com.colegio.asistencia.persistence.jpa.entity.StudentEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SecretaryMapper {

    public static StudentEntity mapStudentRequestDto(StudentRequestDto studentRequestDto) {
        return StudentEntity.builder()
                .dniStudent(studentRequestDto.getDni())
                .name(studentRequestDto.getName())
                .grade(studentRequestDto.getGrade())
                .contactNumber(studentRequestDto.getFamilyContactNumber())
                .residenceAddress(studentRequestDto.getAddress())
                .birthDate(studentRequestDto.getBirthDate())
                .build();
    }
}
