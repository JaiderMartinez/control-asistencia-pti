package com.colegio.asistencia.controllers;

import com.colegio.asistencia.constants.FilePathEnum;
import com.colegio.asistencia.dto.request.UserSaveRequestDto;
import com.colegio.asistencia.exceptions.EmployeeAlreadyExistsException;
import com.colegio.asistencia.exceptions.FieldStructInvalidException;
import com.colegio.asistencia.exceptions.WrongPasswordStructureException;
import com.colegio.asistencia.service.IAdminService;
import com.colegio.asistencia.exceptions.EmptyFieldException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.colegio.asistencia.constants.EndpointPathEnum.PATH_GET_MAPPING_CREATE_USER;
import static com.colegio.asistencia.constants.MessageEnum.MESSAGE_MODEL_ATTRIBUTE_FAILED;
import static com.colegio.asistencia.constants.MessageEnum.MESSAGE_MODEL_ATTRIBUTE_SUCCESS;

@Controller
@RequestMapping(path = "/asistencia/")
@RequiredArgsConstructor
public class AdminController {

    private static final String SUCCESSFULLY_REGISTERED_USER = "Usuario registrado";
    private static final String USER_SUCCESSFULLY_UPDATED = "Usuario con dni: %s actualizado";
    private static final String ATTRIBUTE_SAVE_AND_UPDATE_USER = "userSaveRequestDto";
    private final IAdminService adminService;

    @GetMapping(value = "form-user")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR')")
    public String showViewSaveAUser(Model model) {
        model.addAttribute(ATTRIBUTE_SAVE_AND_UPDATE_USER, new UserSaveRequestDto());
        return FilePathEnum.PATH_TEMPLATE_HTML_FORM_CREATE_ACCOUNT_USER.getMessage();
    }

    @PostMapping(value = "usuario")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR')")
    public String createUserAccount(@ModelAttribute(ATTRIBUTE_SAVE_AND_UPDATE_USER) UserSaveRequestDto userSaveRequest, Model model,
                            RedirectAttributes redirectAttributes) {
        try {
            adminService.saveUser(userSaveRequest);
            redirectAttributes.addFlashAttribute(MESSAGE_MODEL_ATTRIBUTE_SUCCESS.getMessage(), SUCCESSFULLY_REGISTERED_USER);
        } catch (EmptyFieldException | FieldStructInvalidException | WrongPasswordStructureException |
                 EmployeeAlreadyExistsException e) {
            addAttributeWithTheMessagePrefix(model, e.getMessage());
            return FilePathEnum.PATH_TEMPLATE_HTML_FORM_CREATE_ACCOUNT_USER.getMessage();
        }
        return "redirect:" + PATH_GET_MAPPING_CREATE_USER.getMessage();
    }

    private void addAttributeWithTheMessagePrefix(Model model, String message) {
        model.addAttribute(MESSAGE_MODEL_ATTRIBUTE_FAILED.getMessage(), message);
    }

    @GetMapping(value = "usuario/{dni}/editar")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR')")
    public String userData(Model model, @PathVariable(name = "dni") Long dniUser) {
        model.addAttribute(ATTRIBUTE_SAVE_AND_UPDATE_USER, this.adminService.getUserByDni(dniUser));
        return FilePathEnum.PATH_TEMPLATE_HTML_EDIT_USER.getMessage();
    }

    @PostMapping(value = "usuario/actualizar")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR')")
    public String updateUserData(@ModelAttribute(ATTRIBUTE_SAVE_AND_UPDATE_USER) UserSaveRequestDto userSaveRequest, Model model, RedirectAttributes redirectAttributes) {
        try {
            adminService.updateUserData(userSaveRequest);
            redirectAttributes.addFlashAttribute(MESSAGE_MODEL_ATTRIBUTE_SUCCESS.getMessage(), String.format(USER_SUCCESSFULLY_UPDATED, userSaveRequest.getDni()));
        } catch (EmptyFieldException | FieldStructInvalidException | WrongPasswordStructureException |
                 EmployeeAlreadyExistsException e) {
            addAttributeWithTheMessagePrefix(model, e.getMessage());
            return "redirect:/asistencia/usuario/" + userSaveRequest.getDni() + "/editar";
        }
        return "redirect:" + PATH_GET_MAPPING_CREATE_USER.getMessage();
    }
}
