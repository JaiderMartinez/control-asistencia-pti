package com.colegio.asistencia.service.impl;

import com.colegio.asistencia.dto.request.AuthCredentialsRequest;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements ICommonService {

    private final IStudentRepository studentRepository;
    private final IStudentResponseMapper studentResponseMapper;
    private final IEnvironmentPtiRepository environmentPtiRepository;
    private final IEnvironmentPtiResponseMapper environmentPtiResponseMapper;
    private final IUserRepository userRepository;
    private final IUserMapperResponse userMapperResponse;
    private final AuthenticationManager authenticationManager;

    @Override
    public List<SearchFoundStudentResponseDto> findByDniStudentStartingWith(String dniStudent) {
        List<StudentEntity> listOfSearchFoundForStudents = studentRepository.findByDniStudentStartingWith(dniStudent);
        if (listOfSearchFoundForStudents.isEmpty() )
            throw new DataNotFoundException("No se encontro ningun estudiante que en su dni comience con el valor '" + dniStudent + "'");
        return listOfSearchFoundForStudents.stream().map(studentResponseMapper::toSearchFoundStudentResponseDto).toList();
    }

    @Override
    public List<EnvironmentsOfPTIResponseDto> findAllEnvironments() {
        List<EnvironmentPtiEntity> listOfEnvironmentsPtiEntities = environmentPtiRepository.findAll();
        return listOfEnvironmentsPtiEntities.stream().map(environmentPtiResponseMapper::toEnvironmentsOfPTIResponseDto).toList();
    }

    @Override
    public UserAsEmployeeResponseDto findUserAuthenticatedByUserDni(Long dni) {
        return this.userMapperResponse.toUserAsEmployeeResponseDto(this.userRepository.findByEmployeeEntityDni(dni));
    }

    public void singIn(AuthCredentialsRequest authCredentialsRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authCredentialsRequest.getDni(), authCredentialsRequest.getPassword()));
    }
}
