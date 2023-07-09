package com.colegio.asistencia.service;

import com.colegio.asistencia.dto.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.dto.request.StudentRequestDto;
import com.colegio.asistencia.exceptions.PersonAlreadyExistsException;

public interface ISecretaryService {

    void saveEnvironmentPti(CreateEnvironmentPtiRequestDto createEnvironmentPtiRequest);

    void registerStudent(StudentRequestDto studentRequestDto) throws PersonAlreadyExistsException;
}
