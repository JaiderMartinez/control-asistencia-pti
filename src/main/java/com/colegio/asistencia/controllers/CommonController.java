package com.colegio.asistencia.controllers;

import com.colegio.asistencia.models.Constants;
import com.colegio.asistencia.dtos.request.AuthCredentials;
import com.colegio.asistencia.dtos.request.SearchByDniStudentsRequestDto;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.services.ICommonService;
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
        return Constants.PATH_TEMPLATE_HTML_SEARCH_STUDENTS_BY_DNI;
    }

    @PostMapping(value = "estudiantes/encontrar")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR') or hasRole('SECRETARIA')")
    public String searchStudentsByDniPrefix(@ModelAttribute("searchByDniStudentsRequestDto") SearchByDniStudentsRequestDto searchStudents, Model model) {
        try {
            model.addAttribute(ATTRIBUTE_NAME_SEARCH_STUDENTS, this.commonService.getAllStudentsThatBeginWithDni(searchStudents.getDniStudent()));
        } catch (DataNotFoundException e) {
            addAttributeWithTheMessagePrefix(model, e.getMessage());
            return Constants.PATH_TEMPLATE_HTML_SEARCH_STUDENTS_BY_DNI;
        }
        return Constants.PATH_TEMPLATE_HTML_SEARCH_STUDENTS_BY_DNI;
    }

    private void addAttributeWithTheMessagePrefix(Model model, String message) {
        model.addAttribute(Constants.MESSAGE_MODEL_ATTRIBUTE_FAILED, message);
    }

    @GetMapping(value = "inicio")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR') or hasRole('SECRETARIA') or hasRole('DOCENTE')")
    public String showViewIndex(Model model) {
        try {
            String dniFromUserAuthenticated = SecurityContextHolder.getContext().getAuthentication().getName();
            Constants.addAttributesOfTheMenu(model);
            model.addAttribute("userAsEmployeeResponseDto", this.commonService.findUserAuthenticatedByDni(Long.valueOf(dniFromUserAuthenticated)));
            model.addAttribute(ATTRIBUTE_NAME_ENVIRONMENTS, this.commonService.findAllEnvironments());
        } catch (DataNotFoundException e) {
            addAttributeWithTheMessagePrefix(model, e.getMessage());
            return Constants.PATH_TEMPLATE_HTML_INDEX;
        }
        return Constants.PATH_TEMPLATE_HTML_INDEX;
    }

    @GetMapping(value = "login")
    public String login(Model model) {
        model.addAttribute(ATTRIBUTE_NAME_AUTH_CREDENTIALS, new AuthCredentials());
        return Constants.PATH_TEMPLATE_HTML_FORM_LOGIN;
    }

    @PostMapping(value = "sing-in")
    public String signIn(@ModelAttribute("authCredentials") AuthCredentials authCredentials, Model model, HttpServletRequest request) {
        try {
            this.commonService.singIn(authCredentials, request);
            return "redirect:/asistencia/comun/inicio";
        } catch (AuthenticationException e) {
            model.addAttribute(Constants.MESSAGE_MODEL_ATTRIBUTE_FAILED, Constants.MESSAGE_BAD_CREDENTIALS);
            return Constants.PATH_TEMPLATE_HTML_FORM_LOGIN;
        }
    }
}
