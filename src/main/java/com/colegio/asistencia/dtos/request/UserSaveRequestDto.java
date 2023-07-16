package com.colegio.asistencia.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserSaveRequestDto {

    private Long idUser;
    private Long dni;
    private String name;
    private String lastName;
    private String mail;
    private String cellPhone;
    private String employeeRole;
    private String password;
}
