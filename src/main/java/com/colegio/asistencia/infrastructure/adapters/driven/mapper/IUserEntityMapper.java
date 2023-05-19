package com.colegio.asistencia.infrastructure.adapters.driven.mapper;

import com.colegio.asistencia.domain.model.UserModel;
import com.colegio.asistencia.infrastructure.adapters.driven.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IUserEntityMapper {

    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "employeeRole", source = "employeeRole")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "employeeModel", source = "employeeEntity")
    UserModel toUserModel(UserEntity UserEntity);

    @Mapping(target = "idUser", source = "idUser")
    @Mapping(target = "employeeRole", source = "employeeRole")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "employeeEntity", source = "employeeModel")
    UserEntity toUserEntity(UserModel UserModel);
}
