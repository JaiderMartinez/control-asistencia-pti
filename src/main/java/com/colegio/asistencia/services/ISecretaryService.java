package com.colegio.asistencia.services;

import com.colegio.asistencia.dtos.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.dtos.request.StudentRequestDto;
import com.colegio.asistencia.dtos.request.StudentUpdateRequest;
import com.colegio.asistencia.dtos.response.StudentResponse;
import com.colegio.asistencia.exceptions.PersonAlreadyExistsException;
import com.colegio.asistencia.exceptions.PersonNotExistsException;

public interface ISecretaryService {

    void saveEnvironmentPti(CreateEnvironmentPtiRequestDto createEnvironmentPtiRequest);

    void registerStudent(StudentRequestDto studentRequestDto) throws PersonAlreadyExistsException;

    StudentResponse findStudentByDni(String dni)  throws PersonNotExistsException;

    void updateStudentData(StudentUpdateRequest studentUpdateRequest);
}
