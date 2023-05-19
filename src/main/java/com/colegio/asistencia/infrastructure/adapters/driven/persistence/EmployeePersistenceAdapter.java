package com.colegio.asistencia.infrastructure.adapters.driven.persistence;

import com.colegio.asistencia.domain.model.EmployeeModel;
import com.colegio.asistencia.domain.spi.IEmployeePersistencePort;
import com.colegio.asistencia.infrastructure.adapters.driven.mapper.IEmployeeEntityMapper;
import com.colegio.asistencia.infrastructure.adapters.driven.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements IEmployeePersistencePort {

    private final IEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeEntityMapper;

    @Override
    public EmployeeModel saveEmployee(EmployeeModel employeeModel) {
        return employeeEntityMapper.toEmployeeModel(employeeRepository.save(employeeEntityMapper.toEmployeeEntity(employeeModel)));
    }
}
