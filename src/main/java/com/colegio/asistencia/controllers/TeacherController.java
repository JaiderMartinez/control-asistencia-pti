package com.colegio.asistencia.controllers;

import com.colegio.asistencia.models.constants.EndpointPathEnum;
import com.colegio.asistencia.dtos.response.TakeAttendanceEnvironmentResponse;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.services.ITeacherService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.util.List;

import static com.colegio.asistencia.models.constants.EndpointPathEnum.PATH_GET_MAPPING_REPORT;
import static com.colegio.asistencia.models.constants.FilePathEnum.PATH_TEMPLATE_HTML_GENERATE_REPORT;
import static com.colegio.asistencia.models.constants.FilePathEnum.PATH_TEMPLATE_HTML_SHOW_STUDENTS;
import static com.colegio.asistencia.models.constants.MessageEnum.MESSAGE_MODEL_ATTRIBUTE_FAILED;
import static com.colegio.asistencia.models.constants.MessageEnum.MESSAGE_REPORT_GENERATED_SUCCESSFULLY;

@Controller
@RequestMapping(path = "/asistencia/docente")
@RequiredArgsConstructor
public class TeacherController {

    private static final String ATTRIBUTE_NAME_ENVIRONMENTS = "listOfEnvironmentsPTI";
    private static final String REDIRECT = "redirect:";
    private final ITeacherService teacherService;

    @GetMapping(value = "/reporte")
    @PreAuthorize(value = "hasRole('DOCENTE')")
    public String report(Model model, @ModelAttribute("message") String messageError) {
        String dniFromUserAuthenticated = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute(ATTRIBUTE_NAME_ENVIRONMENTS, this.teacherService.getAllEnvironmentsByDniOfTheTeacher(Long.valueOf(dniFromUserAuthenticated)));
        sendAttributeWithMessage(model, messageError);
        addAttributesOfTheMenu(model);
        return PATH_TEMPLATE_HTML_GENERATE_REPORT.getMessage();
    }

    @PostMapping(value = "/generar-reporte")
    @PreAuthorize(value = "hasRole('DOCENTE')")
    public String generatedReport(@RequestParam Long codePti, @RequestParam String pathToSavedFile, RedirectAttributes redirectAttributes) {
        try {
            this.teacherService.generatedReportInFormatPdf(codePti, pathToSavedFile);
        } catch (FileNotFoundException | DocumentException | UsernameNotFoundException | DataNotFoundException | IllegalArgumentException e) {
            addFlashAttribute(redirectAttributes, e.getMessage());
            return REDIRECT + PATH_GET_MAPPING_REPORT.getMessage();
        }
        addFlashAttribute(redirectAttributes, MESSAGE_REPORT_GENERATED_SUCCESSFULLY.getMessage());
        return REDIRECT + PATH_GET_MAPPING_REPORT.getMessage();
    }

    private void addFlashAttribute(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(MESSAGE_MODEL_ATTRIBUTE_FAILED.getMessage(), message);
    }

    private void sendAttributeWithMessage(Model model, String message) {
        model.addAttribute(MESSAGE_MODEL_ATTRIBUTE_FAILED.getMessage(), message);
    }

    private void addAttributesOfTheMenu(Model model) {
        model.addAttribute("formUserUrl", EndpointPathEnum.PATH_GET_MAPPING_CREATE_USER.getMessage());
        model.addAttribute("formSearchStudentsUrl", EndpointPathEnum.PATH_GET_MAPPING_FILTER_STUDENTS.getMessage());
        model.addAttribute("formEnvironmentsUrl", EndpointPathEnum.PATH_GET_MAPPING_CREATE_ENVIRONMENT.getMessage());
        model.addAttribute("formStudent", EndpointPathEnum.PATH_GET_MAPPING_STUDENT.getMessage());
        model.addAttribute("formCreateReport", EndpointPathEnum.PATH_GET_MAPPING_REPORT.getMessage());
    }

    @GetMapping(value = "/ambiente/{codePti}/estudiantes")
    @PreAuthorize(value = "hasRole('DOCENTE')")
    public ModelAndView showStudentsOfTheAEnvironmentPTI(@PathVariable(name = "codePti") Long codePti) {
        TakeAttendanceEnvironmentResponse studentsOfTheAnEnvironment = this.teacherService.getAllStudentsInAnEnvironmentPti(codePti);
        return new ModelAndView(PATH_TEMPLATE_HTML_SHOW_STUDENTS.getMessage())
                .addObject("students", studentsOfTheAnEnvironment.getStudents())
                .addObject("ambiente", studentsOfTheAnEnvironment.getEnvironmentName());
    }

    @PostMapping(value = "/ambiente/guardando/estudiantes")
    @PreAuthorize(value = "hasRole('DOCENTE')")
    public String saveAttendance(@RequestParam("studentsDni") List<Long> studentsDni,
                                 @RequestParam("studentAttendance") List<String> typesOfStudentAttendances) {
        this.teacherService.saveStudentsAttendances(studentsDni, typesOfStudentAttendances);
        return REDIRECT + EndpointPathEnum.PATH_GET_MAPPING_INDEX.getMessage();
    }
}
