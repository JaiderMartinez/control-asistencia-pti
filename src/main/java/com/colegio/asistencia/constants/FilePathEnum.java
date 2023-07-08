package com.colegio.asistencia.constants;

public enum FilePathEnum {

    PATH_TEMPLATE_HTML_SEARCH_STUDENTS_BY_DNI("common/search-students-by-dni"),
    PATH_TEMPLATE_HTML_INDEX("index/index"),
    PATH_TEMPLATE_HTML_FORM_REGISTER_ENVIRONMENT("secretary/formulario-ambientes"),
    PATH_TEMPLATE_HTML_FORM_CREATE_ACCOUNT_USER("admin/formulario-registro-usuario"),
    PATH_TEMPLATE_HTML_FORM_LOGIN("/index/inicio-sesion"),
    PATH_TEMPLATE_HTML_EDIT_USER("admin/modificar-usuario"),
    PATH_TEMPLATE_HTML_GENERATE_REPORT("teacher/generar-reporte");

    private final String message;

    FilePathEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
