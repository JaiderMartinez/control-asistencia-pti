package com.colegio.asistencia.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCredentialsRequest {

    private String dni;
    private String password;
}
