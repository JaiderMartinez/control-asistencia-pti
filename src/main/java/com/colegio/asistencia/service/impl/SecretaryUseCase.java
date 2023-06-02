package com.colegio.asistencia.service.impl;

import com.colegio.asistencia.dto.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.entity.EmployeeEntity;
import com.colegio.asistencia.entity.EnvironmentPtiEntity;
import com.colegio.asistencia.entity.UserEntity;
import com.colegio.asistencia.exceptions.PersonNotExistsException;
import com.colegio.asistencia.mapper.request.IEnvironmentRequestMapper;
import com.colegio.asistencia.repository.IEmployeeRepository;
import com.colegio.asistencia.repository.IEnvironmentPtiRepository;
import com.colegio.asistencia.repository.IUserRepository;
import com.colegio.asistencia.service.ISecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecretaryUseCase implements ISecretaryService {

    private final IEnvironmentPtiRepository environmentPtiRepository;
    private final IEnvironmentRequestMapper environmentRequestMapper;
    private final IEmployeeRepository employeeRepository;
    private final IUserRepository userRepository;

    @Override
    public void saveEnvironmentPti(CreateEnvironmentPtiRequestDto createEnvironmentPtiRequest) {
        final EmployeeEntity employeeFoundByDni = this.employeeRepository.findById(Long.valueOf(createEnvironmentPtiRequest.getDni()))
                                .orElseThrow(() ->  new PersonNotExistsException("No se encontro el docente"));
        final UserEntity userFoundByEntityFromEmployee = userRepository.findByEmployeeEntity(employeeFoundByDni);
        EnvironmentPtiEntity environmentPtiRequestEntity = this.environmentRequestMapper.toEnvironmentPtiEntity(createEnvironmentPtiRequest);
        environmentPtiRequestEntity.setUserEntity(userFoundByEntityFromEmployee);
        this.environmentPtiRepository.save(environmentPtiRequestEntity);
    }
}
