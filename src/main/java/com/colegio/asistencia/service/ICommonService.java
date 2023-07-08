package com.colegio.asistencia.service;

import com.colegio.asistencia.dto.request.AuthCredentials;
import com.colegio.asistencia.dto.request.UserAsEmployeeResponseDto;
import com.colegio.asistencia.dto.response.EnvironmentOfPTIResponseDto;
import com.colegio.asistencia.dto.response.SearchFoundStudentResponseDto;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICommonService {

    List<SearchFoundStudentResponseDto> getAllStudentsThatBeginWithDni(String dniStudent);

    List<EnvironmentOfPTIResponseDto> findAllEnvironments();

    UserAsEmployeeResponseDto findUserAuthenticatedByDni(Long dni);

    Authentication singIn(AuthCredentials authCredentialRequest, HttpServletRequest request);
}
