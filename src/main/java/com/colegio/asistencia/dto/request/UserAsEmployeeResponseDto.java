package com.colegio.asistencia.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAsEmployeeResponseDto {

    private Long dni;
    private String name;
    private String lastName;
    private String mail;
    private String cellPhone;
    private String employeeRole;
}
