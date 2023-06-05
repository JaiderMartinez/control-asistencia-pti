package com.colegio.asistencia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEnvironmentPtiRequestDto {

    private String dni;
    private String name;
    private String description;
    private String classroom;
}
