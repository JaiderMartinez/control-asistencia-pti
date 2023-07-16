package com.colegio.asistencia.utils.mapper;

import com.colegio.asistencia.dtos.request.UserAsEmployeeResponseDto;
import com.colegio.asistencia.dtos.request.UserSaveRequestDto;
import com.colegio.asistencia.models.entity.EmployeeEntity;
import com.colegio.asistencia.models.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserSaveRequestDto mapUserEntityToUserSaveRequestDto(UserEntity user) {
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

    public static UserAsEmployeeResponseDto toUserAsEmployeeResponseDto(UserEntity userEntity) {
        return UserAsEmployeeResponseDto.builder()
                .dni(userEntity.getEmployeeEntity().getDni())
                .name(userEntity.getEmployeeEntity().getName())
                .lastName(userEntity.getEmployeeEntity().getLastName())
                .cellPhone(userEntity.getEmployeeEntity().getCellPhone())
                .employeeRole(userEntity.getEmployeeRole())
                .mail(userEntity.getEmployeeEntity().getMail())
                .build();
    }
}
