package com.colegio.asistencia.models;

import org.springframework.ui.Model;

public class Constants {

    public static final String PATH_GET_MAPPING_CREATE_USER = "/asistencia/form-user";
    public static final String PATH_GET_MAPPING_FILTER_STUDENTS = "/asistencia/comun/estudiantes/filtro";
    public static final String PATH_GET_MAPPING_CREATE_ENVIRONMENT = "/asistencia/show-form-environments";
    public static final String PATH_GET_MAPPING_LOGIN = "/asistencia/comun/login";
    public static final String PATH_GET_MAPPING_INDEX = "/asistencia/comun/inicio";
    public static final String PATH_GET_MAPPING_REPORT = "/asistencia/docente/reporte";
    public static final String PATH_GET_MAPPING_STUDENT = "/asistencia/estudiante";
    public static final String MESSAGE_ENVIRONMENTS_OF_PTI_EMPTY = "No se encontro ningun ambiente";
    public static final String MESSAGE_BAD_CREDENTIALS = "Malas Credenciales";
    public static final String MESSAGE_REPORT_GENERATED_SUCCESSFULLY = "Archivo generado exitosamente";
    public static final String MESSAGE_MODEL_ATTRIBUTE_SUCCESS = "successfulMessage";
    public static final String MESSAGE_MODEL_ATTRIBUTE_FAILED = "message";
    public static final String PATH_TEMPLATE_HTML_FORM_REGISTER_ENVIRONMENT = "secretary/formulario-ambientes";
    public static final String PATH_TEMPLATE_HTML_SEARCH_STUDENTS_BY_DNI = "common/search-students-by-dni";
    public static final String PATH_TEMPLATE_HTML_INDEX = "index/index";
    public static final String PATH_TEMPLATE_HTML_FORM_CREATE_ACCOUNT_USER = "admin/formulario-registro-usuario";
    public static final String PATH_TEMPLATE_HTML_FORM_LOGIN = "index/inicio-sesion";
    public static final String PATH_TEMPLATE_HTML_EDIT_USER = "admin/modificar-usuario";
    public static final String PATH_TEMPLATE_HTML_GENERATE_REPORT = "teacher/generar-reporte";
    public static final String PATH_TEMPLATE_HTML_FORM_REGISTER_STUDENT = "secretary/registrar-estudiante";
    public static final String PATH_TEMPLATE_HTML_FORM_UPDATED_STUDENT = "secretary/modificar-estudiante";
    public static final String PATH_TEMPLATE_HTML_SHOW_STUDENTS = "teacher/asistencia";
    public static final String REDIRECT = "redirect:";

    private Constants() { }

    public static void addAttributesOfTheMenu(Model model) {
        model.addAttribute("formUserUrl", Constants.PATH_GET_MAPPING_CREATE_USER);
        model.addAttribute("formSearchStudentsUrl", Constants.PATH_GET_MAPPING_FILTER_STUDENTS);
        model.addAttribute("formEnvironmentsUrl", Constants.PATH_GET_MAPPING_CREATE_ENVIRONMENT);
        model.addAttribute("formStudent", Constants.PATH_GET_MAPPING_STUDENT);
        model.addAttribute("formCreateReport", Constants.PATH_GET_MAPPING_REPORT);
    }
}
