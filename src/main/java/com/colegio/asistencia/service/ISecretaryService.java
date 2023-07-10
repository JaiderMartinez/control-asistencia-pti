package com.colegio.asistencia.service;

import com.colegio.asistencia.dto.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.dto.request.StudentRequestDto;
import com.colegio.asistencia.dto.request.StudentUpdateRequest;
import com.colegio.asistencia.dto.response.StudentResponse;
import com.colegio.asistencia.exceptions.PersonAlreadyExistsException;
import com.colegio.asistencia.exceptions.PersonNotExistsException;

public interface ISecretaryService {

    void saveEnvironmentPti(CreateEnvironmentPtiRequestDto createEnvironmentPtiRequest);

    void registerStudent(StudentRequestDto studentRequestDto) throws PersonAlreadyExistsException;

    StudentResponse findStudentByDni(String dni)  throws PersonNotExistsException;

    void updateStudentData(StudentUpdateRequest studentUpdateRequest);
}
