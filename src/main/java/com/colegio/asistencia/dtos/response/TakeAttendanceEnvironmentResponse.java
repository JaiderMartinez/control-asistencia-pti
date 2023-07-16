package com.colegio.asistencia.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TakeAttendanceEnvironmentResponse {

    private String environmentName;
    private List<StudentResponse> students;
}
