package com.colegio.asistencia.infrastructure.adapters.driven.mapper;

import com.colegio.asistencia.domain.model.EmployeeModel;
import com.colegio.asistencia.infrastructure.adapters.driven.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IEmployeeEntityMapper {

    EmployeeModel toEmployeeModel(EmployeeEntity employeeEntity);

    EmployeeEntity toEmployeeEntity(EmployeeModel employeeModel);
}
