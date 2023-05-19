package com.colegio.asistencia.application.handler;

import com.colegio.asistencia.application.dto.UserSaveRequestDto;

public interface IAdminService {

    void saveUser(UserSaveRequestDto userSaveRequestDto);
}
