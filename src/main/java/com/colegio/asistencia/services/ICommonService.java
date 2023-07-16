package com.colegio.asistencia.services;

import com.colegio.asistencia.dtos.request.AuthCredentials;
import com.colegio.asistencia.dtos.request.UserAsEmployeeResponseDto;
import com.colegio.asistencia.dtos.response.EnvironmentOfPTIResponseDto;
import com.colegio.asistencia.dtos.response.SearchFoundStudentResponseDto;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICommonService {

    List<SearchFoundStudentResponseDto> getAllStudentsThatBeginWithDni(String dniStudent);

    List<EnvironmentOfPTIResponseDto> findAllEnvironments();

    UserAsEmployeeResponseDto findUserAuthenticatedByDni(Long dni);

    Authentication singIn(AuthCredentials authCredentialRequest, HttpServletRequest request);
}
