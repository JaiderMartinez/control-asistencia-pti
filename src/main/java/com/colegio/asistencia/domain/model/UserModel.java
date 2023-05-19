package com.colegio.asistencia.domain.model;

public record UserModel(Long idUser, String employeeRole, String password, EmployeeModel employeeModel) {
}
