package com.colegio.asistencia.persistence.criteria;

import com.colegio.asistencia.persistence.criteria.dto.StudentAttendanceDto;

import java.util.List;

public interface IStudentCriteriaRepository {

    List<StudentAttendanceDto> getAllStudentAttendanceDetails(Long codeEnvironmentPti);
}
