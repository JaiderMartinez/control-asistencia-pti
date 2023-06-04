package com.colegio.asistencia.controllers;

import com.colegio.asistencia.constants.Constants;
import com.colegio.asistencia.dto.request.SearchByDniStudentsRequestDto;
import com.colegio.asistencia.dto.request.UserAsEmployeeResponseDto;
import com.colegio.asistencia.dto.response.EnvironmentsOfPTIResponseDto;
import com.colegio.asistencia.dto.response.SearchFoundStudentResponseDto;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.service.ICommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/asistencia/")
@RequiredArgsConstructor
public class CommonController {

    private final ICommonService commonService;

    @GetMapping(value = "list-students")
    public String showViewSearchStudentsByDni(Model model) {
        model.addAttribute("searchByDniStudentsRequestDto", new SearchByDniStudentsRequestDto());
        return Constants.PATH_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
    }

    @PostMapping(value = "search-student")
    public String listEmployee(@ModelAttribute("searchByDniStudentsRequestDto") SearchByDniStudentsRequestDto searchStudents, Model model) {
        try {
            final List<SearchFoundStudentResponseDto> listOfSearchFoundForStudents =  commonService.findByDniStudentStartingWith(searchStudents.getDniStudent());
            model.addAttribute("listOfStudents", listOfSearchFoundForStudents);
            return Constants.PATH_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
        } catch (DataNotFoundException e) {
            model.addAttribute(Constants.MESSAGE_MODEL_ATTRIBUTE.getMessage(), e.getMessage());
            return Constants.PATH_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
        }
    }

    @GetMapping(value = "index")
    public String showViewIndex(Model model) {
        try {
            String dniFromUserAuthenticated = SecurityContextHolder.getContext().getAuthentication().getName();
            final UserAsEmployeeResponseDto userAuthenticated = commonService.findUserAuthenticatedByUserDni(Long.valueOf(dniFromUserAuthenticated));
            model.addAttribute("userAsEmployeeResponseDto", userAuthenticated);
            List<EnvironmentsOfPTIResponseDto> listFoundOfTheNameEnvironments = commonService.findAllEnvironments();
            if (listFoundOfTheNameEnvironments.isEmpty()) {
                model.addAttribute(Constants.MESSAGE_MODEL_ATTRIBUTE.getMessage(), Constants.MESSAGE_ENVIRONMENTS_OF_PTI_EMPTY.getMessage());
            } else {
                model.addAttribute("listOfEnvironmentsPTI", listFoundOfTheNameEnvironments);
            }
            return Constants.PATH_HTML_INDEX.getMessage();
        } catch (DataNotFoundException e) {
            model.addAttribute(Constants.MESSAGE_MODEL_ATTRIBUTE.getMessage(), e.getMessage());
            return Constants.PATH_HTML_INDEX.getMessage();
        }
    }
}
