package com.colegio.asistencia.domain.spi;

import com.colegio.asistencia.domain.model.EmployeeModel;

public interface IEmployeePersistencePort {

    EmployeeModel saveEmployee(EmployeeModel employeeModel);
}
