package com.colegio.asistencia.service;

import com.colegio.asistencia.dto.response.EnvironmentsOfPTIResponseDto;
import com.colegio.asistencia.dto.response.SearchFoundStudentResponseDto;

import java.util.List;

public interface ICommonService {

    List<SearchFoundStudentResponseDto> findByDniStudentStartingWith(String dniStudent);

    List<EnvironmentsOfPTIResponseDto> findAllEnvironments();
}
