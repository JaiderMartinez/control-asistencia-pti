package com.colegio.asistencia.service.impl;

import com.colegio.asistencia.dto.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.dto.request.StudentRequestDto;
import com.colegio.asistencia.dto.request.StudentUpdateRequest;
import com.colegio.asistencia.dto.response.StudentResponse;
import com.colegio.asistencia.exceptions.PersonAlreadyExistsException;
import com.colegio.asistencia.mapper.SecretaryMapper;
import com.colegio.asistencia.persistence.jpa.entity.EmployeeEntity;
import com.colegio.asistencia.persistence.jpa.entity.EnvironmentPtiEntity;
import com.colegio.asistencia.persistence.jpa.entity.StudentEntity;
import com.colegio.asistencia.persistence.jpa.entity.UserEntity;
import com.colegio.asistencia.exceptions.PersonNotExistsException;
import com.colegio.asistencia.mapper.request.IEnvironmentRequestMapper;
import com.colegio.asistencia.persistence.jpa.repository.IEmployeeRepository;
import com.colegio.asistencia.persistence.jpa.repository.IEnvironmentPtiRepository;
import com.colegio.asistencia.persistence.jpa.repository.IStudentRepository;
import com.colegio.asistencia.persistence.jpa.repository.IUserRepository;
import com.colegio.asistencia.service.ISecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SecretaryUseCase implements ISecretaryService {

    private static final String MESSAGE_STUDENT_ALREADY_EXIST = "El estudiante con esta identificacion %s ya existe";
    private static final String MESSAGE_STUDENT_NOT_FOUND = "El estudiante con esta identificacion %s no se encuentra";
    private final IEnvironmentPtiRepository environmentPtiRepository;
    private final IEnvironmentRequestMapper environmentRequestMapper;
    private final IEmployeeRepository employeeRepository;
    private final IUserRepository userRepository;
    private final IStudentRepository studentRepository;

    @Transactional
    @Override
    public void saveEnvironmentPti(CreateEnvironmentPtiRequestDto createEnvironmentPtiRequest) {
        final EmployeeEntity employeeFoundByDni = this.employeeRepository.findById(Long.valueOf(createEnvironmentPtiRequest.getDni()))
                                .orElseThrow(() ->  new PersonNotExistsException("No se encontro el docente"));
        final UserEntity userFoundByEntityFromEmployee = userRepository.findByEmployeeEntity(employeeFoundByDni);
        EnvironmentPtiEntity environmentPtiRequestEntity = this.environmentRequestMapper.toEnvironmentPtiEntity(createEnvironmentPtiRequest);
        environmentPtiRequestEntity.setUserEntity(userFoundByEntityFromEmployee);
        this.environmentPtiRepository.save(environmentPtiRequestEntity);
    }

    @Override
    public void registerStudent(StudentRequestDto studentRequestDto) throws PersonAlreadyExistsException {
        existStudentWithEqualDni(studentRequestDto.getDni());
        this.studentRepository.save( SecretaryMapper.mapStudentRequestDtoToStudentEntity(studentRequestDto) );
    }

    private void existStudentWithEqualDni(String dni) throws PersonAlreadyExistsException {
        if (this.studentRepository.existsById(dni) ) {
            throw new PersonAlreadyExistsException(String.format(MESSAGE_STUDENT_ALREADY_EXIST, dni));
        }
    }

    @Override
    public StudentResponse findStudentByDni(String dni) throws PersonNotExistsException {
        return SecretaryMapper.mapStudentEntityToStudentResponse( getStudentByDni(dni) );
    }

    private StudentEntity getStudentByDni(String dni) {
        return this.studentRepository.findById(dni)
                .orElseThrow(() -> new PersonNotExistsException(String.format(MESSAGE_STUDENT_NOT_FOUND, dni)));
    }

    @Override
    public void updateStudentData(StudentUpdateRequest studentUpdateRequest) {
        StudentEntity studentFound = getStudentByDni(studentUpdateRequest.getDni());
        studentFound.setGrade(studentUpdateRequest.getGrade());
        studentFound.setContactNumber(studentUpdateRequest.getFamilyContactNumber());
        studentFound.setResidenceAddress(studentUpdateRequest.getAddress());
        this.studentRepository.save(studentFound);
    }
}
