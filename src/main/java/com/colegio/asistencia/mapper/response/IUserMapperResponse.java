package com.colegio.asistencia.mapper.response;

import com.colegio.asistencia.dto.request.UserAsEmployeeResponseDto;
import com.colegio.asistencia.persistence.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IUserMapperResponse {

    @Mapping(target = "dni", source = "employeeEntity.dni")
    @Mapping(target = "name", source = "employeeEntity.name")
    @Mapping(target = "lastName", source = "employeeEntity.lastName")
    @Mapping(target = "mail", source = "employeeEntity.mail")
    @Mapping(target = "cellPhone", source = "employeeEntity.cellPhone")
    UserAsEmployeeResponseDto toUserAsEmployeeResponseDto(UserEntity userEntity);
}
