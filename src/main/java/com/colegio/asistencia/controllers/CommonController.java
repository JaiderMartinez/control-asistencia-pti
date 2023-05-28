package com.colegio.asistencia.controllers;

import com.colegio.asistencia.dto.request.SearchByDniStudentsRequestDto;
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
        return "common/search-students-by-dni";
    }

    @PostMapping(value = "search-student")
    public String listEmployee(@ModelAttribute("searchByDniStudentsRequestDto") SearchByDniStudentsRequestDto searchStudents, Model model) {
        try {
            final List<SearchFoundStudentResponseDto> listOfSearchFoundForStudents =  commonService.findByDniStudentStartingWith(searchStudents.getDniStudent());
            model.addAttribute("listOfStudents", listOfSearchFoundForStudents);
            return "common/search-students-by-dni";
        } catch (DataNotFoundException e) {
            model.addAttribute("message", e.getMessage());
            return "common/search-students-by-dni";
        }
    }
}
