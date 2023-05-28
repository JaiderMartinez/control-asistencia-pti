package com.colegio.asistencia.controllers;

import com.colegio.asistencia.dto.request.UserSaveRequestDto;
import com.colegio.asistencia.exceptions.FieldStructInvalidException;
import com.colegio.asistencia.service.IAdminService;
import com.colegio.asistencia.exceptions.EmptyFieldException;
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
public class AdminController {

    private final IAdminService adminService;

    @GetMapping(value = "form-save-user")
    public String showViewSaveAUser(Model model) {
        model.addAttribute("userSaveRequestDto", new UserSaveRequestDto());
        return "admin/formulario-registro-usuario";
    }

    @PostMapping(value = "save-user")
    public String saveUsers(@ModelAttribute("userSaveRequestDto") UserSaveRequestDto userSaveRequest, Model model) {
        try {
            adminService.saveUser(userSaveRequest);
            return "redirect:/asistencia/form-save-user";
        } catch (EmptyFieldException | FieldStructInvalidException e) {
            model.addAttribute("message", e.getMessage());
            return "admin/formulario-registro-usuario";
        }
    }
}
