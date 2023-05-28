package com.colegio.asistencia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchFoundStudentResponseDto {

    private String dniStudent;
    private String name;
    private String grado;
    private String contactNumber;
    private String residenceAddress;
    private LocalDate birthdate;
}
