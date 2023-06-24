package com.colegio.asistencia.controllers;

import com.colegio.asistencia.constants.FilePathConstants;
import com.colegio.asistencia.constants.MessageConstants;
import com.colegio.asistencia.dto.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.exceptions.EmptyFieldException;
import com.colegio.asistencia.exceptions.FieldStructInvalidException;
import com.colegio.asistencia.exceptions.PersonNotExistsException;
import com.colegio.asistencia.service.ISecretaryService;
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

    @GetMapping(value = "show-form-environments")
    @PreAuthorize(value = "hasRole('SECRETARIA')")
    public String showFormCreateEnvironmentsPTI(Model model) {
        model.addAttribute("createEnvironmentPtiRequestDto", new CreateEnvironmentPtiRequestDto());
        return FilePathConstants.PATH_FILE_TEMPLATES_HTML_FORM_REGISTER_ENVIRONMENT.getMessage();
    }

    @PostMapping(value = "create-environment")
    @PreAuthorize(value = "hasRole('SECRETARIA')")
    public String saveEnvironmentsPTI(Model model, @ModelAttribute("createEnvironmentPtiRequestDto") CreateEnvironmentPtiRequestDto createEnvironmentPtiRequestDto) {
        try {
            secretaryService.saveEnvironmentPti(createEnvironmentPtiRequestDto);
            return "redirect:/asistencia/show-form-environments";
        } catch (EmptyFieldException | FieldStructInvalidException | PersonNotExistsException e) {
            model.addAttribute(MessageConstants.MESSAGE_MODEL_ATTRIBUTE.getMessage(), e.getMessage());
            return FilePathConstants.PATH_FILE_TEMPLATES_HTML_FORM_REGISTER_ENVIRONMENT.getMessage();
        }
    }

}
