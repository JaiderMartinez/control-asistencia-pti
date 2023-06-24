package com.colegio.asistencia.constants;

public enum FilePathConstants {

    PATH_FILE_TEMPLATES_HTML_SEARCH_STUDENTS_BY_DNI("common/search-students-by-dni"),
    PATH_FILE_TEMPLATES_HTML_INDEX("index/index"),
    PATH_FILE_TEMPLATES_HTML_FORM_REGISTER_ENVIRONMENT("secretary/formulario-ambientes");

    private final String message;

    FilePathConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
