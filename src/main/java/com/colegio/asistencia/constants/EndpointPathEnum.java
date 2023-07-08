package com.colegio.asistencia.constants;

public enum EndpointPathEnum {

    PATH_GET_MAPPING_CREATE_USER("/asistencia/form-user"),
    PATH_GET_MAPPING_FILTER_STUDENTS("/asistencia/comun/estudiantes/filtro"),
    PATH_GET_MAPPING_CREATE_ENVIRONMENT("/asistencia/show-form-environments"),
    PATH_GET_MAPPING_LOGIN("/asistencia/comun/login"),
    PATH_GET_MAPPING_INDEX("/asistencia/comun/inicio"),
    PATH_GET_MAPPING_REPORT("/asistencia/docente/reporte");


    private final String message;

    EndpointPathEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
