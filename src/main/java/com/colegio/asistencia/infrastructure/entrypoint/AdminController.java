package com.colegio.asistencia.infrastructure.entrypoint;

import com.colegio.asistencia.application.dto.UserSaveRequestDto;
import com.colegio.asistencia.application.handler.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/asistencia/")
@RequiredArgsConstructor
public class AdminController {

    private final IAdminService adminService;

    @PostMapping(value = "save-user")
    public String saveUsers(@RequestBody UserSaveRequestDto userSaveRequest, Errors errors) {
        adminService.saveUser(userSaveRequest);
        if(errors.hasErrors()) {
            return "ubicacion del HTML";
        }
        adminService.saveUser(userSaveRequest);
        return "redirect:/a que ruta de http lo voy a redirigir despues de crear al usuario";
    }
}
