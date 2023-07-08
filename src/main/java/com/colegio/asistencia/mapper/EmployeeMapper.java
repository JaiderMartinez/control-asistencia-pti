package com.colegio.asistencia.mapper;

import com.colegio.asistencia.dto.request.UserSaveRequestDto;
import com.colegio.asistencia.persistence.jpa.entity.EmployeeEntity;
import com.colegio.asistencia.persistence.jpa.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeMapper {

    public static UserSaveRequestDto mapUserEntityToUserSave(UserEntity user) {
        return UserSaveRequestDto.builder()
                .idUser(user.getIdUser())
                .dni(user.getEmployeeEntity().getDni())
                .name(user.getEmployeeEntity().getName())
                .lastName(user.getEmployeeEntity().getLastName())
                .mail(user.getEmployeeEntity().getMail())
                .cellPhone(user.getEmployeeEntity().getCellPhone())
                .employeeRole(user.getEmployeeRole())
                .password(user.getPassword())
                .build();
    }

    public static UserEntity mapUserSaveRequestDtoToUserEntity(UserSaveRequestDto userSaveRequestDto) {
        return UserEntity.builder()
                .idUser(userSaveRequestDto.getIdUser())
                .employeeRole(userSaveRequestDto.getEmployeeRole())
                .password(userSaveRequestDto.getPassword())
                .employeeEntity(mapUserSaveRequestDtoToEmployeeEntity(userSaveRequestDto))
                .build();
    }

    public static EmployeeEntity mapUserSaveRequestDtoToEmployeeEntity(UserSaveRequestDto userSaveRequestDto) {
        return EmployeeEntity.builder()
                .dni(userSaveRequestDto.getDni())
                .name(userSaveRequestDto.getName())
                .lastName(userSaveRequestDto.getLastName())
                .mail(userSaveRequestDto.getMail())
                .cellPhone(userSaveRequestDto.getCellPhone())
                .build();
    }
}
