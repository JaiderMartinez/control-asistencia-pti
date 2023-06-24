package com.colegio.asistencia.controllers;

import com.colegio.asistencia.constants.ControllerPathConstants;
import com.colegio.asistencia.constants.FilePathConstants;
import com.colegio.asistencia.constants.MessageConstants;
import com.colegio.asistencia.dto.request.SearchByDniStudentsRequestDto;
import com.colegio.asistencia.dto.request.UserAsEmployeeResponseDto;
import com.colegio.asistencia.dto.response.EnvironmentsOfPTIResponseDto;
import com.colegio.asistencia.dto.response.SearchFoundStudentResponseDto;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.service.ICommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/asistencia/comun/")
@RequiredArgsConstructor
public class CommonController {

    private final ICommonService commonService;

    @GetMapping(value = "estudiantes/filtro")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR') or hasRole('SECRETARIA')")
    public String showViewSearchStudentsByDni(Model model) {
        model.addAttribute("searchByDniStudentsRequestDto", new SearchByDniStudentsRequestDto());
        return FilePathConstants.PATH_FILE_TEMPLATES_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
    }

    @PostMapping(value = "estudiantes/encontrar")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR') or hasRole('SECRETARIA')")
    public String searchStudentsByLikeDni(@ModelAttribute("searchByDniStudentsRequestDto") SearchByDniStudentsRequestDto searchStudents, Model model) {
        try {
            final List<SearchFoundStudentResponseDto> listOfSearchFoundForStudents =  this.commonService.findByDniStudentStartingWith(searchStudents.getDniStudent());
            model.addAttribute("listOfStudents", listOfSearchFoundForStudents);
            return FilePathConstants.PATH_FILE_TEMPLATES_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
        } catch (DataNotFoundException e) {
            model.addAttribute(MessageConstants.MESSAGE_MODEL_ATTRIBUTE.getMessage(), e.getMessage());
            return FilePathConstants.PATH_FILE_TEMPLATES_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
        }
    }

    @GetMapping(value = "inicio")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR') or hasRole('SECRETARIA') or hasRole('DOCENTE')")
    public String showViewIndex(Model model) {
        try {
            String dniFromUserAuthenticated = SecurityContextHolder.getContext().getAuthentication().getName();
            final UserAsEmployeeResponseDto userAuthenticated = commonService.findUserAuthenticatedByUserDni(Long.valueOf(dniFromUserAuthenticated));
            model.addAttribute("userAsEmployeeResponseDto", userAuthenticated);
            model.addAttribute("formUserUrl", ControllerPathConstants.PATH_GET_MAPPING_CREATE_USER.getMessage());
            model.addAttribute("formSearchStudentsUrl", ControllerPathConstants.PATH_GET_MAPPING_FILTER_STUDENTS.getMessage());
            model.addAttribute("formEnvironmentsUrl", ControllerPathConstants.PATH_GET_MAPPING_CREATE_ENVIRONMENT.getMessage());
            List<EnvironmentsOfPTIResponseDto> listFoundOfTheNameEnvironments = commonService.findAllEnvironments();
            if (listFoundOfTheNameEnvironments.isEmpty()) {
                model.addAttribute(MessageConstants.MESSAGE_MODEL_ATTRIBUTE.getMessage(), MessageConstants.MESSAGE_ENVIRONMENTS_OF_PTI_EMPTY.getMessage());
            } else {
                model.addAttribute("listOfEnvironmentsPTI", listFoundOfTheNameEnvironments);
            }
            return FilePathConstants.PATH_FILE_TEMPLATES_HTML_INDEX.getMessage();
        } catch (DataNotFoundException e) {
            model.addAttribute(MessageConstants.MESSAGE_MODEL_ATTRIBUTE.getMessage(), e.getMessage());
            return FilePathConstants.PATH_FILE_TEMPLATES_HTML_INDEX.getMessage();
        }
    }
}
