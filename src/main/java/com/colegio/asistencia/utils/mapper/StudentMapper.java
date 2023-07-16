package com.colegio.asistencia.utils.mapper;

import com.colegio.asistencia.dtos.request.StudentRequestDto;
import com.colegio.asistencia.dtos.response.SearchFoundStudentResponseDto;
import com.colegio.asistencia.dtos.response.StudentResponse;
import com.colegio.asistencia.dtos.response.TakeAttendanceEnvironmentResponse;
import com.colegio.asistencia.models.entity.StudentEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class StudentMapper {

    public static StudentEntity mapStudentRequestDtoToStudentEntity(StudentRequestDto studentRequestDto) {
        return StudentEntity.builder()
                .dniStudent(studentRequestDto.getDni())
                .name(studentRequestDto.getName())
                .grade(studentRequestDto.getGrade())
                .contactNumber(studentRequestDto.getFamilyContactNumber())
                .residenceAddress(studentRequestDto.getAddress())
                .birthDate(studentRequestDto.getBirthDate())
                .build();
    }

    public static StudentResponse mapStudentEntityToStudentResponse(StudentEntity studentEntity) {
        return StudentResponse.builder()
                .dni(studentEntity.getDniStudent())
                .address(studentEntity.getResidenceAddress())
                .name(studentEntity.getName())
                .lastName(studentEntity.getLastName())
                .grade(studentEntity.getGrade())
                .familyContactNumber(studentEntity.getContactNumber())
                .birthDate(studentEntity.getBirthDate())
                .build();
    }

    public static SearchFoundStudentResponseDto toSearchFoundStudentResponseDto(StudentEntity studentEntity) {
        return SearchFoundStudentResponseDto.builder()
                .dniStudent(studentEntity.getDniStudent())
                .name(studentEntity.getName())
                .grade(studentEntity.getGrade())
                .contactNumber(studentEntity.getContactNumber())
                .residenceAddress(studentEntity.getResidenceAddress())
                .birthDate(studentEntity.getBirthDate())
                .build();
    }

    public static TakeAttendanceEnvironmentResponse studentEntityToTakeAttendanceEnvironmentResponse(List<StudentEntity> studentEntities) {
        return TakeAttendanceEnvironmentResponse.builder()
                .environmentName(studentEntities.get(0).getEnvironmentPtiEntity().getName())
                .students(studentEntities.stream()
                        .map(StudentMapper::mapStudentEntityToStudentResponse)
                        .toList())
                .build();
    }

    public static StudentEntity toStudentEntity(Long studentDni) {
        return StudentEntity.builder()
                .dniStudent(studentDni.toString())
                .build();
    }
}
