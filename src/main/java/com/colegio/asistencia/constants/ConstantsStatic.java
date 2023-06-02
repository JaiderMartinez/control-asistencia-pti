package com.colegio.asistencia.constants;

public enum ConstantsStatic {

    MESSAGE_MODEL_ATTRIBUTE("message"),
    MESSAGE_ENVIRONMENTS_OF_PTI_EMPTY("No se encontro ningun ambiente"),
    PATH_HTML_SEARCH_STUDENTS_BY_DNI("common/search-students-by-dni"),
    PATH_HTML_INDEX("index/index"),
    PATH_HTML_FORM_REGISTER_ENVIRONMENT("secretary/formulario-ambientes");

    private final String message;

    ConstantsStatic(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
