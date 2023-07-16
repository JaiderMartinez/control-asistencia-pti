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
public class CreateEnvironmentPtiRequestDto {

    private String dni;
    private String name;
    private String description;
    private String classroom;
}
