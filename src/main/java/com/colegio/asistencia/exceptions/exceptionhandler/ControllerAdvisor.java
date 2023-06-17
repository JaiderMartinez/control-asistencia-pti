package com.colegio.asistencia.exceptions.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ResponseStatusException.class)
    public String handleNoFoundException(ResponseStatusException responseStatusException, Model model) {
        if (responseStatusException.getStatus() == HttpStatus.NOT_FOUND) {
            model.addAttribute("errorMessage", "La página solicitada no se encuentra.");
            return "error/errorNotFound";
        }
        return "error/errorNotFound";
    }
}
