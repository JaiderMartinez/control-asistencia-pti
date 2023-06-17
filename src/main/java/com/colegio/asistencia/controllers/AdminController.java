package com.colegio.asistencia.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/asistencia/")
@RequiredArgsConstructor
public class AdminController {

    private final IAdminService adminService;

    @GetMapping(value = "form-user")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR')")
    public String showViewSaveAUser(Model model) {
        model.addAttribute("userSaveRequestDto", new UserSaveRequestDto());
        return "admin/formulario-registro-usuario";
    }

    @PostMapping(value = "usuario")
    @PreAuthorize(value = "hasRole('ADMINISTRADOR')")
    public String saveUsers(@ModelAttribute("userSaveRequestDto") UserSaveRequestDto userSaveRequest, Model model,
                            RedirectAttributes redirectAttributes) {
        try {
            adminService.saveUser(userSaveRequest);
            redirectAttributes.addFlashAttribute("successfulMessage", "Usuario registrado");
            return "redirect:/asistencia/form-user";
        } catch (EmptyFieldException | FieldStructInvalidException | WrongPasswordStructureException |
                 EmployeeAlreadyExistsException e) {
            model.addAttribute("message", e.getMessage());
            return "admin/formulario-registro-usuario";
        }
    }
}
