package com.colegio.asistencia.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SearchFoundStudentResponseDto {

    private String dniStudent;
    private String name;
    private String grade;
    private String contactNumber;
    private String residenceAddress;
    private LocalDate birthDate;
}
