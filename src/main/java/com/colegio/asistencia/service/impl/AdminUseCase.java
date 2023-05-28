package com.colegio.asistencia.service.impl;

import com.colegio.asistencia.dto.request.UserSaveRequestDto;
import com.colegio.asistencia.entity.EmployeeEntity;
import com.colegio.asistencia.entity.UserEntity;
import com.colegio.asistencia.exceptions.EmptyFieldException;
import com.colegio.asistencia.exceptions.FieldStructInvalidException;
import com.colegio.asistencia.mapper.request.IUserRequestMapper;
import com.colegio.asistencia.repository.IEmployeeRepository;
import com.colegio.asistencia.repository.IUserRepository;
import com.colegio.asistencia.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUseCase implements IAdminService {

    private final IUserRepository userPersistence;
    private final IEmployeeRepository employeePersistence;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void saveUser(UserSaveRequestDto userSaveRequest) {
        if (isFieldsAreEmpty(userSaveRequest)) {
            throw new EmptyFieldException("Los campos no pueden estar vacios");
        } else if (!isValidFieldStructDniAndCellPhoneAndPassword(userSaveRequest.getDni(), userSaveRequest.getCellPhone(),
                userSaveRequest.getPassword())) {
            throw new FieldStructInvalidException("El estructura del dni, telefono o contraseña pueden ser incorrectas");
        }
        final UserEntity userRequestEntity = userRequestMapper.toUserEntity(userSaveRequest);
        employeePersistence.save(userRequestEntity.getEmployeeEntity());
        userPersistence.save(userRequestEntity);
    }

    private boolean isFieldsAreEmpty(UserSaveRequestDto user) {
        return (user.getPassword().replace(" ", "").isEmpty()  ||
                user.getEmployeeRole().replace(" ", "").isEmpty() ||
                user.getName().replace(" ", "").isEmpty() ||
                user.getLastName().replace(" ", "").isEmpty() ||
                user.getMail().replace(" ", "").isEmpty() ||
                user.getCellPhone().replace(" ", "").isEmpty() ||
                user.getDni() == null);
    }

    private boolean isValidFieldStructDniAndCellPhoneAndPassword(Long dni, String cellPhone, String password) {
        return password.contains(".")  || dni.toString().length() <= 10 || cellPhone.length() == 10;
    }
}