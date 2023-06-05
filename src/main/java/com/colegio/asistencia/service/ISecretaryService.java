package com.colegio.asistencia.service;

import com.colegio.asistencia.dto.request.CreateEnvironmentPtiRequestDto;

public interface ISecretaryService {

    void saveEnvironmentPti(CreateEnvironmentPtiRequestDto createEnvironmentPtiRequest);
}
