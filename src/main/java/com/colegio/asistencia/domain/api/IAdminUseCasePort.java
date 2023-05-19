package com.colegio.asistencia.domain.api;

import com.colegio.asistencia.domain.model.UserModel;

public interface IAdminUseCasePort {

    void saveUser(UserModel userModel);
}
