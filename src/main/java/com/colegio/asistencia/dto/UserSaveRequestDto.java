package com.colegio.asistencia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSaveRequestDto {
    private Long dni;
    private String name;
    private String lastName;
    private String mail;
    private String cellPhone;
    private String employeeRole;
    private String password;
}
