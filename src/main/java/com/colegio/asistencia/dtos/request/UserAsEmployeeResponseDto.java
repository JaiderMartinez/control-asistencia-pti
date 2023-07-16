package com.colegio.asistencia.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAsEmployeeResponseDto {

    private Long dni;
    private String name;
    private String lastName;
    private String mail;
    private String cellPhone;
    private String employeeRole;
}
