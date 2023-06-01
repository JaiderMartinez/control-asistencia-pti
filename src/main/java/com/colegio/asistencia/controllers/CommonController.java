package com.colegio.asistencia.controllers;

import com.colegio.asistencia.constants.ConstantsStatic;
import com.colegio.asistencia.dto.request.SearchByDniStudentsRequestDto;
import com.colegio.asistencia.dto.response.EnvironmentsOfPTIResponseDto;
import com.colegio.asistencia.dto.response.SearchFoundStudentResponseDto;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.service.ICommonService;
import lombok.RequiredArgsConstructor;
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
        return ConstantsStatic.PATH_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
    }

    @PostMapping(value = "search-student")
    public String listEmployee(@ModelAttribute("searchByDniStudentsRequestDto") SearchByDniStudentsRequestDto searchStudents, Model model) {
        try {
            final List<SearchFoundStudentResponseDto> listOfSearchFoundForStudents =  commonService.findByDniStudentStartingWith(searchStudents.getDniStudent());
            model.addAttribute("listOfStudents", listOfSearchFoundForStudents);
            return ConstantsStatic.PATH_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
        } catch (DataNotFoundException e) {
            model.addAttribute(ConstantsStatic.MESSAGE_MODEL_ATTRIBUTE.getMessage(), e.getMessage());
            return ConstantsStatic.PATH_HTML_SEARCH_STUDENTS_BY_DNI.getMessage();
        }
    }

    @GetMapping(value = "index")
    public String showViewIndex(Model model) {
        try {
            List<EnvironmentsOfPTIResponseDto> listFoundOfTheNameEnvironments = commonService.findAllEnvironments();
            if (listFoundOfTheNameEnvironments.isEmpty()) {
                model.addAttribute(ConstantsStatic.MESSAGE_MODEL_ATTRIBUTE.getMessage(), ConstantsStatic.MESSAGE_ENVIRONMENTS_OF_PTI_EMPTY.getMessage());
            } else {
                model.addAttribute("listOfEnvironmentsPTI", listFoundOfTheNameEnvironments);
            }
            return ConstantsStatic.PATH_HTML_INDEX.getMessage();
        } catch (DataNotFoundException e) {
            model.addAttribute(ConstantsStatic.MESSAGE_MODEL_ATTRIBUTE.getMessage(), e.getMessage());
            return ConstantsStatic.PATH_HTML_INDEX.getMessage();
        }
    }
}
