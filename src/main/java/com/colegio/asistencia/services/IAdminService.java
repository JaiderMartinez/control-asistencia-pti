package com.colegio.asistencia.services;

import com.colegio.asistencia.dtos.request.UserSaveRequestDto;

public interface IAdminService {

    void saveUser(UserSaveRequestDto userSaveRequest);

    UserSaveRequestDto getUserByDni(Long dni);

    void updateUserData(UserSaveRequestDto userSaveRequestDto);
}
