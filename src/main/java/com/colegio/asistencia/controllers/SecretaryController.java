package com.colegio.asistencia.controllers;

import com.colegio.asistencia.constants.FilePathEnum;
import com.colegio.asistencia.constants.MessageEnum;
import com.colegio.asistencia.dto.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.exceptions.EmptyFieldException;
import com.colegio.asistencia.exceptions.FieldStructInvalidException;
import com.colegio.asistencia.exceptions.PersonNotExistsException;
import com.colegio.asistencia.service.ISecretaryService;
import com.colegio.asistencia.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/asistencia/")
@RequiredArgsConstructor
public class SecretaryController {

    private final ISecretaryService secretaryService;
    private final ITeacherService teacherService;

    @GetMapping(value = "show-form-environments")
    @PreAuthorize(value = "hasRole('SECRETARIA')")
    public String showFormCreateEnvironmentsPTI(Model model) {
        model.addAttribute("createEnvironmentPtiRequestDto", new CreateEnvironmentPtiRequestDto());
        return FilePathEnum.PATH_TEMPLATE_HTML_FORM_REGISTER_ENVIRONMENT.getMessage();
    }

    @PostMapping(value = "create-environment")
    @PreAuthorize(value = "hasRole('SECRETARIA')")
    public String createEnvironment(Model model, @ModelAttribute("createEnvironmentPtiRequestDto") CreateEnvironmentPtiRequestDto createEnvironmentPtiRequestDto) {
        try {
            secretaryService.saveEnvironmentPti(createEnvironmentPtiRequestDto);
        } catch (EmptyFieldException | FieldStructInvalidException | PersonNotExistsException e) {
            model.addAttribute(MessageEnum.MESSAGE_MODEL_ATTRIBUTE_FAILED.getMessage(), e.getMessage());
            return FilePathEnum.PATH_TEMPLATE_HTML_FORM_REGISTER_ENVIRONMENT.getMessage();
        }
        return "redirect:/asistencia/show-form-environments";
    }
}
