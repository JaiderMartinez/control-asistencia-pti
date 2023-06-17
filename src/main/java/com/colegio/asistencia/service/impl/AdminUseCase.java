package com.colegio.asistencia.service.impl;

import com.colegio.asistencia.dto.request.UserSaveRequestDto;
import com.colegio.asistencia.entity.UserEntity;
import com.colegio.asistencia.exceptions.EmployeeAlreadyExistsException;
import com.colegio.asistencia.exceptions.EmptyFieldException;
import com.colegio.asistencia.exceptions.FieldStructInvalidException;
import com.colegio.asistencia.exceptions.WrongPasswordStructureException;
import com.colegio.asistencia.mapper.request.IUserRequestMapper;
import com.colegio.asistencia.repository.IEmployeeRepository;
import com.colegio.asistencia.repository.IUserRepository;
import com.colegio.asistencia.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AdminUseCase implements IAdminService {

    private final IUserRepository userPersistence;
    private final IEmployeeRepository employeePersistence;
    private final IUserRequestMapper userRequestMapper;
    private final PasswordEncoder passwordEncoder;
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Override
    public void saveUser(UserSaveRequestDto userSaveRequest) {
        validationsWhenSavingAUser(userSaveRequest);
        userSaveRequest.setPassword(passwordEncoder.encode(userSaveRequest.getPassword()));
        final UserEntity userRequestEntity = this.userRequestMapper.toUserEntity(userSaveRequest);
        this.employeePersistence.save(userRequestEntity.getEmployeeEntity());
        this.userPersistence.save(userRequestEntity);
    }

    private void validationsWhenSavingAUser(UserSaveRequestDto userToValidate) {
        if (isFieldsAreEmpty(userToValidate)) {
            throw new EmptyFieldException("Los campos no pueden estar vacios");
        } else if (isValidFieldStructDniAndCellPhone(userToValidate.getDni(), userToValidate.getCellPhone())) {
            throw new FieldStructInvalidException("El estructura del dni o telefono pueden ser incorrectas");
        } else if (isValidFieldPassword(userToValidate.getPassword())) {
            throw new WrongPasswordStructureException("La estructura de la contraseña es incorrecta " + userToValidate.getPassword() + " ejemplo Abc123@#");
        } else if (this.employeePersistence.findById(userToValidate.getDni()).isPresent()) {
            throw new EmployeeAlreadyExistsException("El empleado con esa identificacion ya existe");
        }
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

    private boolean isValidFieldStructDniAndCellPhone(Long dni, String cellPhone) {
        if (dni.toString().length() <= 10 && cellPhone.length() == 10) {
            return false;
        }
        return true;
    }

    private boolean isValidFieldPassword(String password) {
        if (Pattern.matches(PASSWORD_PATTERN, password) && password.length() <= 50) {
            return false;
        }
        return true;
    }
}