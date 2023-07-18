package com.colegio.asistencia.controllers;

import com.colegio.asistencia.models.Constants;
import com.colegio.asistencia.dtos.request.CreateEnvironmentPtiRequestDto;
import com.colegio.asistencia.dtos.request.StudentRequestDto;
import com.colegio.asistencia.dtos.request.StudentUpdateRequest;
import com.colegio.asistencia.exceptions.EmptyFieldException;
import com.colegio.asistencia.exceptions.FieldStructInvalidException;
import com.colegio.asistencia.exceptions.PersonAlreadyExistsException;
import com.colegio.asistencia.exceptions.PersonNotExistsException;
import com.colegio.asistencia.services.ISecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        Constants.addAttributesOfTheMenu(model);
        return Constants.PATH_TEMPLATE_HTML_FORM_REGISTER_ENVIRONMENT;
    }

    @PostMapping(value = "create-environment")
    @PreAuthorize(value = "hasRole('SECRETARIA')")
    public String createEnvironment(Model model, @ModelAttribute("createEnvironmentPtiRequestDto") CreateEnvironmentPtiRequestDto createEnvironmentPtiRequestDto) {
        try {
            secretaryService.saveEnvironmentPti(createEnvironmentPtiRequestDto);
        } catch (EmptyFieldException | FieldStructInvalidException | PersonNotExistsException e) {
            sendAttributeWithMessage(model, e.getMessage());
            return Constants.PATH_TEMPLATE_HTML_FORM_REGISTER_ENVIRONMENT;
        }
        return Constants.REDIRECT + "/asistencia/show-form-environments";
    }

    @GetMapping(value = "estudiante")
    @PreAuthorize(value = "hasRole('SECRETARIA')")
    public String showStudentRegistrationForm(Model model) {
        model.addAttribute("studentRequestDto", new StudentRequestDto());
        Constants.addAttributesOfTheMenu(model);
        model.addAttribute("environments", this.secretaryService.getAllEnvironments());
        return Constants.PATH_TEMPLATE_HTML_FORM_REGISTER_STUDENT;
    }

    @PostMapping(value = "estudiante/")
    @PreAuthorize(value = "hasRole('SECRETARIA')")
    public String registerStudent(Model model, @ModelAttribute("studentRequestDto") StudentRequestDto studentRequestDto) {
        try {
            secretaryService.registerStudent(studentRequestDto);
        } catch (PersonAlreadyExistsException e) {
            sendAttributeWithMessage(model, e.getMessage());
            return Constants.PATH_TEMPLATE_HTML_FORM_REGISTER_STUDENT;
        }
        return Constants.REDIRECT + Constants.PATH_GET_MAPPING_STUDENT;
    }

    private void sendAttributeWithMessage(Model model, String messageError) {
        model.addAttribute(Constants.MESSAGE_MODEL_ATTRIBUTE_FAILED, messageError);
    }

    @GetMapping(value = "estudiante/editar/{dniStudent}")
    @PreAuthorize(value = "hasRole('SECRETARIA')")
    public String showStudentDataByDni(Model model, @PathVariable(name = "dniStudent") String dniStudent) {
        Constants.addAttributesOfTheMenu(model);
        try {
            model.addAttribute("studentUpdateRequest", this.secretaryService.findStudentByDni(dniStudent));
        } catch ( PersonNotExistsException e) {
            sendAttributeWithMessage(model, e.getMessage());
            return Constants.PATH_TEMPLATE_HTML_FORM_UPDATED_STUDENT;
        }
        model.addAttribute("environments", this.secretaryService.getAllEnvironments());
        return Constants.PATH_TEMPLATE_HTML_FORM_UPDATED_STUDENT;
    }

    @PostMapping(value = "estudiante/actualizado")
    @PreAuthorize(value = "hasRole('SECRETARIA')")
    public String editStudentData(Model model, @ModelAttribute("studentUpdateRequest") StudentUpdateRequest studentUpdateRequest) {
        try {
            this.secretaryService.updateStudentData(studentUpdateRequest);
        } catch (PersonAlreadyExistsException e) {
            sendAttributeWithMessage(model, e.getMessage());
            return Constants.PATH_TEMPLATE_HTML_FORM_UPDATED_STUDENT;
        }
        return Constants.REDIRECT + Constants.PATH_GET_MAPPING_STUDENT;
    }
}
