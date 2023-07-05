package com.colegio.asistencia.service.impl;

import com.colegio.asistencia.constants.MessageEnum;
import com.colegio.asistencia.dto.request.AuthCredentials;
import com.colegio.asistencia.dto.request.UserAsEmployeeResponseDto;
import com.colegio.asistencia.dto.response.EnvironmentsOfPTIResponseDto;
import com.colegio.asistencia.dto.response.SearchFoundStudentResponseDto;
import com.colegio.asistencia.entity.EnvironmentPtiEntity;
import com.colegio.asistencia.entity.StudentEntity;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.mapper.response.IEnvironmentPtiResponseMapper;
import com.colegio.asistencia.mapper.response.IStudentResponseMapper;
import com.colegio.asistencia.mapper.response.IUserMapperResponse;
import com.colegio.asistencia.repository.IEnvironmentPtiRepository;
import com.colegio.asistencia.repository.IStudentRepository;
import com.colegio.asistencia.repository.IUserRepository;
import com.colegio.asistencia.service.ICommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements ICommonService {

    private static final String MESSAGE_STUDENTS_NOT_FOUND = "No se encontro ningun estudiante que en su dni comience con el valor '%s'";
    private final IStudentRepository studentRepository;
    private final IStudentResponseMapper studentResponseMapper;
    private final IEnvironmentPtiRepository environmentPtiRepository;
    private final IEnvironmentPtiResponseMapper environmentPtiResponseMapper;
    private final IUserRepository userRepository;
    private final IUserMapperResponse userMapperResponse;
    private final AuthenticationManager authenticationManager;

    @Override
    public List<SearchFoundStudentResponseDto> getAllStudentsThatBeginWithDni(String dniStudent) {
        List<StudentEntity> listOfSearchFoundForStudents = studentRepository.findByDniStudentStartingWith(dniStudent);
        if (listOfSearchFoundForStudents.isEmpty() )
            throw new DataNotFoundException(String.format(MESSAGE_STUDENTS_NOT_FOUND, dniStudent));
        return listOfSearchFoundForStudents.stream().map(studentResponseMapper::toSearchFoundStudentResponseDto).toList();
    }

    @Override
    public List<EnvironmentsOfPTIResponseDto> findAllEnvironments() {
        List<EnvironmentPtiEntity> listOfEnvironmentsPtiEntities = environmentPtiRepository.findAll();
        if (listOfEnvironmentsPtiEntities.isEmpty() )
            throw new DataNotFoundException(MessageEnum.MESSAGE_ENVIRONMENTS_OF_PTI_EMPTY.getMessage());
        return listOfEnvironmentsPtiEntities.stream().map(environmentPtiResponseMapper::toEnvironmentsOfPTIResponseDto).toList();
    }

    @Override
    public UserAsEmployeeResponseDto findUserAuthenticatedByDni(Long dni) {
        return this.userMapperResponse.toUserAsEmployeeResponseDto(this.userRepository.findByEmployeeEntityDni(dni).get());
    }

    @Override
    public Authentication singIn(AuthCredentials authCredentialRequest, HttpServletRequest request) throws AuthenticationException {
        Authentication userAuthenticated = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authCredentialRequest.getUsername(), authCredentialRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(userAuthenticated);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        return userAuthenticated;
    }
}
