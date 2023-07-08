package com.colegio.asistencia.controllers;

import com.colegio.asistencia.constants.EndpointPathEnum;
import com.colegio.asistencia.dto.request.AuthCredentials;
import com.colegio.asistencia.dto.request.SearchByDniStudentsRequestDto;
import com.colegio.asistencia.dto.request.UserAsEmployeeResponseDto;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.service.ICommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.colegio.asistencia.constants.FilePathEnum.PATH_TEMPLATE_HTML_FORM_LOGIN;
import static com.colegio.asistencia.constants.FilePathEnum.PATH_TEMPLATE_HTML_INDEX;
import static com.colegio.asistencia.constants.FilePathEnum.PATH_TEMPLATE_HTML_SEARCH_STUDENTS_BY_DNI;
import static com.colegio.asistencia.constants.MessageEnum.MESSAGE_BAD_CREDENTIALS;
import static com.colegio.asistencia.constants.MessageEnum.MESSAGE_MODEL_ATTRIBUTE_FAILED;

@Controller
@RequestMapping(path = "/asistencia/comun/")
@RequiredArgsConstructor
@Slf4j
public class CommonController {

    private static final String ATTRIBUTE_NAME_ENVIRONMENTS = "listOfEnvironmentsPTI";
    private static final String ATTRIBUTE_NAME_AUTH_CREDENTIALS = "authCredentials";
    private static final String ATTRIBUTE_NAME_SEARCH_STUDENTS = "listOfStudents";
    private final ICommonService commonService;

    @GetMapping(value = "estudiantes/filtro")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR') or hasRole('SECRETARIA')")
    public String showViewSearchStudentsByDni(Model model) {
        model.addAttribute("searchByDniStudentsRequestDto", new SearchByDniStudentsRequestDto());
        return PATH_TEMPLATE_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
    }

    @PostMapping(value = "estudiantes/encontrar")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR') or hasRole('SECRETARIA')")
    public String searchStudentsByDniPrefix(@ModelAttribute("searchByDniStudentsRequestDto") SearchByDniStudentsRequestDto searchStudents, Model model) {
        try {
            model.addAttribute(ATTRIBUTE_NAME_SEARCH_STUDENTS, this.commonService.getAllStudentsThatBeginWithDni(searchStudents.getDniStudent()));
            return PATH_TEMPLATE_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
        } catch (DataNotFoundException e) {
            addAttributeWithTheMessagePrefix(model, e.getMessage());
            return PATH_TEMPLATE_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
        }
    }

    private void addAttributeWithTheMessagePrefix(Model model, String message) {
        model.addAttribute(MESSAGE_MODEL_ATTRIBUTE_FAILED.getMessage(), message);
    }

    @GetMapping(value = "inicio")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR') or hasRole('SECRETARIA') or hasRole('DOCENTE')")
    public String showViewIndex(Model model) {
        try {
            String dniFromUserAuthenticated = SecurityContextHolder.getContext().getAuthentication().getName();
            addAttributesToModel(model, commonService.findUserAuthenticatedByDni(Long.valueOf(dniFromUserAuthenticated)));
            model.addAttribute(ATTRIBUTE_NAME_ENVIRONMENTS, this.commonService.findAllEnvironments());
            return PATH_TEMPLATE_HTML_INDEX.getMessage();
        } catch (DataNotFoundException e) {
            addAttributeWithTheMessagePrefix(model, e.getMessage());
            return PATH_TEMPLATE_HTML_INDEX.getMessage();
        }
    }

    private void addAttributesToModel(Model model, UserAsEmployeeResponseDto userAuthenticated) {
        model.addAttribute("userAsEmployeeResponseDto", userAuthenticated);
        model.addAttribute("formUserUrl", EndpointPathEnum.PATH_GET_MAPPING_CREATE_USER.getMessage());
        model.addAttribute("formSearchStudentsUrl", EndpointPathEnum.PATH_GET_MAPPING_FILTER_STUDENTS.getMessage());
        model.addAttribute("formEnvironmentsUrl", EndpointPathEnum.PATH_GET_MAPPING_CREATE_ENVIRONMENT.getMessage());
    }

    @GetMapping(value = "login")
    public String login(Model model) {
        model.addAttribute(ATTRIBUTE_NAME_AUTH_CREDENTIALS, new AuthCredentials());
        return PATH_TEMPLATE_HTML_FORM_LOGIN.getMessage();
    }

    @PostMapping(value = "sing-in")
    public String signIn(@ModelAttribute("authCredentials") AuthCredentials authCredentials, Model model, HttpServletRequest request) {
        try {
            this.commonService.singIn(authCredentials, request);
            return "redirect:/asistencia/comun/inicio";
        } catch (AuthenticationException e) {
            model.addAttribute(MESSAGE_MODEL_ATTRIBUTE_FAILED.getMessage(), MESSAGE_BAD_CREDENTIALS);
            return PATH_TEMPLATE_HTML_FORM_LOGIN.getMessage();
        }
    }
}
