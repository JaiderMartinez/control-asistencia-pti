package com.colegio.asistencia.service;

import com.colegio.asistencia.dto.UserSaveRequestDto;

public interface IAdminService {

    void saveUser(UserSaveRequestDto userSaveRequest);
}
