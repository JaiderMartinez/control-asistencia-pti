package com.colegio.asistencia.controllers;

import com.colegio.asistencia.constants.EndpointPathEnum;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;

import static com.colegio.asistencia.constants.EndpointPathEnum.PATH_GET_MAPPING_REPORT;
import static com.colegio.asistencia.constants.FilePathEnum.PATH_TEMPLATE_HTML_GENERATE_REPORT;
import static com.colegio.asistencia.constants.MessageEnum.MESSAGE_MODEL_ATTRIBUTE_FAILED;
import static com.colegio.asistencia.constants.MessageEnum.MESSAGE_REPORT_GENERATED_SUCCESSFULLY;

@Controller
@RequestMapping(path = "/asistencia/docente")
@RequiredArgsConstructor
public class TeacherController {

    private static final String ATTRIBUTE_NAME_ENVIRONMENTS = "listOfEnvironmentsPTI";
    private final ITeacherService teacherService;

    @GetMapping(value = "/reporte")
    @PreAuthorize(value = "hasRole('DOCENTE')")
    public String report(Model model, @ModelAttribute("message") String messageError) {
        String dniFromUserAuthenticated = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute(ATTRIBUTE_NAME_ENVIRONMENTS, this.teacherService.getAllEnvironmentsByDniOfTheTeacher(Long.valueOf(dniFromUserAuthenticated)));
        addAttributeWithTheMessagePrefix(model, messageError);
        return PATH_TEMPLATE_HTML_GENERATE_REPORT.getMessage();
    }

    @PostMapping(value = "/generar-reporte")
    @PreAuthorize(value = "hasRole('DOCENTE')")
    public String generatedReport(@RequestParam Long codePti, @RequestParam String pathToSavedFile, RedirectAttributes redirectAttributes) {
        try {
            this.teacherService.generatedReportInFormatPdf(codePti, pathToSavedFile);
        } catch (FileNotFoundException | DocumentException | UsernameNotFoundException | DataNotFoundException | IllegalArgumentException e) {
            addFlashAttribute(redirectAttributes, e.getMessage());
            return "redirect:" + PATH_GET_MAPPING_REPORT.getMessage();
        }
        addFlashAttribute(redirectAttributes, MESSAGE_REPORT_GENERATED_SUCCESSFULLY.getMessage());
        return "redirect:" + PATH_GET_MAPPING_REPORT.getMessage();
    }

    private void addFlashAttribute(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(MESSAGE_MODEL_ATTRIBUTE_FAILED.getMessage(), message);
    }

    private void addAttributeWithTheMessagePrefix(Model model, String message) {
        model.addAttribute(MESSAGE_MODEL_ATTRIBUTE_FAILED.getMessage(), message);
    }

    private void addAttributesToModel(Model model) {
        model.addAttribute("formUserUrl", EndpointPathEnum.PATH_GET_MAPPING_CREATE_USER.getMessage());
        model.addAttribute("formSearchStudentsUrl", EndpointPathEnum.PATH_GET_MAPPING_FILTER_STUDENTS.getMessage());
        model.addAttribute("formEnvironmentsUrl", EndpointPathEnum.PATH_GET_MAPPING_CREATE_ENVIRONMENT.getMessage());
        model.addAttribute("formStudent", EndpointPathEnum.PATH_GET_MAPPING_STUDENT.getMessage());
        model.addAttribute("formCreateReport", EndpointPathEnum.PATH_GET_MAPPING_REPORT.getMessage());
    }

    @PostMapping(value = "/estudiantes/finalizar")
    @PreAuthorize(value = "hasRole('DOCENTE')")
    public void recordAttendance() {
    }

}
