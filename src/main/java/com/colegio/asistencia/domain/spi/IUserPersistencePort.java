package com.colegio.asistencia.domain.spi;

import com.colegio.asistencia.domain.model.UserModel;

public interface IUserPersistencePort {

    UserModel saveUser(UserModel userModel);
}
