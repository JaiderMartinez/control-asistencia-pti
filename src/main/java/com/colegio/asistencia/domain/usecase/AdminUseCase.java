package com.colegio.asistencia.domain.usecase;

import com.colegio.asistencia.domain.api.IAdminUseCasePort;
import com.colegio.asistencia.domain.model.UserModel;
import com.colegio.asistencia.domain.spi.IEmployeePersistencePort;
import com.colegio.asistencia.domain.spi.IUserPersistencePort;

public class AdminUseCase implements IAdminUseCasePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEmployeePersistencePort employeePersistencePort;

    public AdminUseCase(IUserPersistencePort userPersistencePort, IEmployeePersistencePort employeePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.employeePersistencePort = employeePersistencePort;
    }

    @Override
    public void saveUser(UserModel userModel) {
        employeePersistencePort.saveEmployee(userModel.employeeModel());
        userPersistencePort.saveUser(userModel);
    }
}
