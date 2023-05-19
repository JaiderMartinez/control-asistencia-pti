package com.colegio.asistencia.application.dto;

public record UserSaveRequestDto(Long dni, String name, String lastName, String mail, String cellPhone, String employeeRole, String password) {
}
