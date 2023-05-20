package com.colegio.asistencia.domain.usecase;

import com.colegio.asistencia.domain.api.IAdminUseCasePort;
import com.colegio.asistencia.domain.exceptions.EmptyFieldException;
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
        if (!isValidateFieldsEmpty(userModel)) {
            throw new EmptyFieldException("Los campos no pueden estar vacios");
        }
        employeePersistencePort.saveEmployee(userModel.employeeModel());
        userPersistencePort.saveUser(userModel);
    }

    private boolean isValidateFieldsEmpty(UserModel user) {
        return !(user.password().replace(" ", "").isEmpty() ||
                user.employeeRole().replace(" ", "").isEmpty() ||
                user.employeeModel().name().replace(" ", "").isEmpty() ||
                user.employeeModel().lastName().replace(" ", "").isEmpty() ||
                user.employeeModel().mail().replace(" ", "").isEmpty() ||
                user.employeeModel().cellPhone().replace(" ", "").isEmpty() ||
                user.employeeModel().dni() == null);
    }
}
