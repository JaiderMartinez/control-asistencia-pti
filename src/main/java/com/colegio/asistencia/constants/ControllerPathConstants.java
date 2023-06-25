package com.colegio.asistencia.constants;

public enum ControllerPathConstants {

    PATH_GET_MAPPING_CREATE_USER("/asistencia/form-user"),
    PATH_GET_MAPPING_FILTER_STUDENTS("/asistencia/comun/estudiantes/filtro"),
    PATH_GET_MAPPING_CREATE_ENVIRONMENT("/asistencia/show-form-environments");


    private final String message;

    ControllerPathConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
