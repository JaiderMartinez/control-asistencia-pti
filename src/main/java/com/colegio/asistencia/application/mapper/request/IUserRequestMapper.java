package com.colegio.asistencia.application.mapper.request;

import com.colegio.asistencia.application.dto.UserSaveRequestDto;
import com.colegio.asistencia.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IUserRequestMapper {

    @Mapping(target = "employeeModel.dni", source = "userSaveRequestDto.dni")
    @Mapping(target = "employeeModel.name", source = "userSaveRequestDto.name")
    @Mapping(target = "employeeModel.lastName", source = "userSaveRequestDto.lastName")
    @Mapping(target = "employeeModel.mail", source = "userSaveRequestDto.mail")
    @Mapping(target = "employeeModel.cellPhone", source = "userSaveRequestDto.cellPhone")
    @Mapping(target = "idUser", ignore = true)
    UserModel toUserModel(UserSaveRequestDto userSaveRequestDto);
}
