package com.colegio.asistencia.service;

import com.colegio.asistencia.dto.request.UserSaveRequestDto;

public interface IAdminService {

    void saveUser(UserSaveRequestDto userSaveRequest);

    UserSaveRequestDto getUserByDni(Long dni);

    void updateUserData(UserSaveRequestDto userSaveRequestDto);
}
