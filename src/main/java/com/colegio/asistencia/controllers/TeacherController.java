package com.colegio.asistencia.controllers;

import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.service.ITeacherService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;

import static com.colegio.asistencia.constants.EndpointPathEnum.PATH_GET_MAPPING_REPORT;
import static com.colegio.asistencia.constants.FilePathEnum.PATH_TEMPLATE_HTML_GENERATE_REPORT;
import static com.colegio.asistencia.constants.MessageEnum.MESSAGE_MODEL_ATTRIBUTE_FAILED;

@Controller
@RequestMapping(path = "/asistencia/docente")
@RequiredArgsConstructor
public class TeacherController {

    private static final String ATTRIBUTE_NAME_ENVIRONMENTS = "listOfEnvironmentsPTI";
    private final ITeacherService teacherService;

    @GetMapping(value = "/reporte")
    @PreAuthorize(value = "hasRole('DOCENTE')")
    public String report(Model model) {
        String dniFromUserAuthenticated = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute(ATTRIBUTE_NAME_ENVIRONMENTS, this.teacherService.getAllEnvironmentsByDniOfTheTeacher(Long.valueOf(dniFromUserAuthenticated)));
        return PATH_TEMPLATE_HTML_GENERATE_REPORT.getMessage();
    }

    @PostMapping(value = "/generar-reporte")
    @PreAuthorize(value = "hasRole('DOCENTE')")
    public String generatedReport(@RequestParam Long codePti, @RequestParam String pathToSavedFile, Model model) {
        try {
            this.teacherService.generatedReportInFormatPdf(codePti, pathToSavedFile);
        } catch (FileNotFoundException | DocumentException | UsernameNotFoundException | DataNotFoundException e) {
            addAttributeWithTheMessagePrefix(model, e.getMessage());
            return PATH_TEMPLATE_HTML_GENERATE_REPORT.getMessage();
        }
        return "redirect:" + PATH_GET_MAPPING_REPORT.getMessage();
    }

    private void addAttributeWithTheMessagePrefix(Model model, String message) {
        model.addAttribute(MESSAGE_MODEL_ATTRIBUTE_FAILED.getMessage(), message);
    }

    @PostMapping(value = "/estudiantes/finalizar")
    @PreAuthorize(value = "hasRole('DOCENTE')")
    public void recordAttendance() {
    }

}
