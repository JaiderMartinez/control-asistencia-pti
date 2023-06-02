package com.colegio.asistencia.controllers;

import com.colegio.asistencia.constants.ConstantsStatic;
import com.colegio.asistencia.dto.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.exceptions.EmptyFieldException;
import com.colegio.asistencia.exceptions.FieldStructInvalidException;
import com.colegio.asistencia.exceptions.PersonNotExistsException;
import com.colegio.asistencia.service.ISecretaryService;
import lombok.RequiredArgsConstructor;
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
    public String showFormCreateEnvironmentsPTI(Model model) {
        model.addAttribute("createEnvironmentPtiRequestDto", new CreateEnvironmentPtiRequestDto());
        return ConstantsStatic.PATH_HTML_FORM_REGISTER_ENVIRONMENT.getMessage();
    }

    @PostMapping(value = "create-environment")
    public String saveEnvironmentsPTI(Model model, @ModelAttribute("createEnvironmentPtiRequestDto") CreateEnvironmentPtiRequestDto createEnvironmentPtiRequestDto) {
        try {
            secretaryService.saveEnvironmentPti(createEnvironmentPtiRequestDto);
            return "redirect:/asistencia/show-form-environments";
        } catch (EmptyFieldException | FieldStructInvalidException | PersonNotExistsException e) {
            model.addAttribute("message", e.getMessage());
            return ConstantsStatic.PATH_HTML_FORM_REGISTER_ENVIRONMENT.getMessage();
        }
    }

}
