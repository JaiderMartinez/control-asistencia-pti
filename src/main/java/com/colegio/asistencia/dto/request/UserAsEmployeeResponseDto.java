package com.colegio.asistencia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAsEmployeeResponseDto {

    private Long dni;
    private String name;
    private String lastName;
    private String mail;
    private String cellPhone;
    private String employeeRole;
}
