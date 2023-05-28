package com.colegio.asistencia.mapper.request;

import com.colegio.asistencia.dto.request.UserSaveRequestDto;
import com.colegio.asistencia.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IUserRequestMapper {

    @Mapping(target = "employeeEntity.dni", source = "userSaveRequestDto.dni")
    @Mapping(target = "employeeEntity.name", source = "userSaveRequestDto.name")
    @Mapping(target = "employeeEntity.lastName", source = "userSaveRequestDto.lastName")
    @Mapping(target = "employeeEntity.mail", source = "userSaveRequestDto.mail")
    @Mapping(target = "employeeEntity.cellPhone", source = "userSaveRequestDto.cellPhone")
    @Mapping(target = "idUser", ignore = true)
    UserEntity toUserEntity(UserSaveRequestDto userSaveRequestDto);
}
