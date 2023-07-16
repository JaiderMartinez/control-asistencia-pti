package com.colegio.asistencia.repositories.criteria;

import com.colegio.asistencia.dtos.response.StudentAttendanceCriteria;

import java.util.List;

public interface IStudentCriteriaRepository {

    List<StudentAttendanceCriteria> getAllStudentAttendanceDetails(Long codeEnvironmentPti);
}
