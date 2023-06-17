package com.colegio.asistencia.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControllerApp implements ErrorController {

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        // Lógica para manejar el error 404 aquí
        return "error/errorNotFound"; // Nombre de la vista de error personalizada
    }
}
