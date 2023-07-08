package com.colegio.asistencia.persistence.criteria.dto;

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
public class StudentAttendanceDto {

    private String studentName;
    private long numberOfFouls;
    private long numberOfAssists;
    private long amountOfClassAvoidance;
    private long numberOfDelays;
}
