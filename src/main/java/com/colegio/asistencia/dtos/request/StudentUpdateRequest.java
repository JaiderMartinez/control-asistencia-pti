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
public class StudentUpdateRequest {

    private String dni;
    private String grade;
    private String familyContactNumber;
    private String address;
    private Long codePti;
}
