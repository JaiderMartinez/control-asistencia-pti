package com.colegio.asistencia.dtos.response;

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
public class StudentAttendanceCriteria {

    private String studentName;
    private long numberOfFouls;
    private long numberOfAssists;
    private long amountOfClassAvoidance;
    private long numberOfDelays;
}
