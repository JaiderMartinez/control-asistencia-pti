package com.colegio.asistencia.service.impl;

import com.colegio.asistencia.dto.request.UserSaveRequestDto;
import com.colegio.asistencia.persistence.jpa.entity.UserEntity;
import com.colegio.asistencia.exceptions.EmployeeAlreadyExistsException;
import com.colegio.asistencia.exceptions.EmptyFieldException;
import com.colegio.asistencia.exceptions.FieldStructInvalidException;
import com.colegio.asistencia.exceptions.WrongPasswordStructureException;
import com.colegio.asistencia.mapper.EmployeeMapper;
import com.colegio.asistencia.mapper.request.IUserRequestMapper;
import com.colegio.asistencia.persistence.jpa.repository.IEmployeeRepository;
import com.colegio.asistencia.persistence.jpa.repository.IUserRepository;
import com.colegio.asistencia.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AdminUseCase implements IAdminService {

    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String USER_NOT_FOUND = "No se encontro al usuario con el dni %s";
    private static final String MESSAGE_EMPTY_FIELD = "Los campos no pueden estar vacios";
    private static final String MESSAGE_FIELD_STRUCT_INVALID = "La estructura del dni o telefono son incorrectas";
    private static final String MESSAGE_WRONG_PASSWORD = "La estructura de la contraseña es incorrecta %s  ejemplo Abc123@#";
    private static final String MESSAGE_EMPLOYEE_ALREADY_EXISTS = "El empleado con esa identificacion ya existe";
    private final IUserRepository userPersistence;
    private final IEmployeeRepository employeePersistence;
    private final IUserRequestMapper userRequestMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void saveUser(UserSaveRequestDto userSaveRequest) {
        validationsWhenSavingAUser(userSaveRequest);
        userSaveRequest.setPassword(passwordEncoder.encode(userSaveRequest.getPassword()));
        final UserEntity userRequestEntity = this.userRequestMapper.toUserEntity(userSaveRequest);
        this.userPersistence.save(userRequestEntity);
    }

    private void validationsWhenSavingAUser(UserSaveRequestDto userToValidate) {
        if (isFieldsAreEmpty(userToValidate)) {
            throw new EmptyFieldException(MESSAGE_EMPTY_FIELD);
        } else if (isValidFieldStructDniAndCellPhone(userToValidate.getDni(), userToValidate.getCellPhone())) {
            throw new FieldStructInvalidException(MESSAGE_FIELD_STRUCT_INVALID);
        } else if (isValidFieldPassword(userToValidate.getPassword())) {
            throw new WrongPasswordStructureException(String.format(MESSAGE_WRONG_PASSWORD, userToValidate.getPassword()));
        } else if (existUserWithSameDni(userToValidate.getDni())) {
            throw new EmployeeAlreadyExistsException(MESSAGE_EMPLOYEE_ALREADY_EXISTS);
        }
    }

    private boolean existUserWithSameDni(Long dni) {
        return this.employeePersistence.findById(dni).isPresent();
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

    @Override
    public UserSaveRequestDto getUserByDni(Long dni) {
        return EmployeeMapper.mapUserEntityToUserSave(
                this.userPersistence.findByEmployeeEntityDni(dni)
                .orElseThrow( () -> new EmptyFieldException(String.format(USER_NOT_FOUND, dni)))
        );
    }

    @Transactional
    @Override
    public void updateUserData(UserSaveRequestDto userSaveRequestDto) {
        isValidFieldStructDniAndCellPhone(userSaveRequestDto.getDni(), userSaveRequestDto.getCellPhone());
        isFieldsAreEmpty(userSaveRequestDto);
        this.userPersistence.save(  EmployeeMapper.mapUserSaveRequestDtoToUserEntity(userSaveRequestDto) );
    }

}