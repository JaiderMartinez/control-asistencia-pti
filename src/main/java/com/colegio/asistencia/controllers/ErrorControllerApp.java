package com.colegio.asistencia.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorControllerApp implements ErrorController {

    private static final String STATUS_PAGE_NOT_FOUND_PATH_FILE_TEMPLATES = "error/status-not-found";
    private static final String STATUS_PAGE_FORBIDDEN_PATH_FILE_TEMPLATES  = "error/status-forbidden";
    private static final String STATUS_PAGE_INTERNAL_SERVER_ERROR_PATH_FILE_TEMPLATES  = "error/status-internal-server-error";

    @RequestMapping("/error")
    public String statusError(HttpServletRequest request) {
        int statusCode = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == HttpServletResponse.SC_NOT_FOUND) {
            return STATUS_PAGE_NOT_FOUND_PATH_FILE_TEMPLATES;
        } else if (statusCode == HttpServletResponse.SC_FORBIDDEN) {
            return STATUS_PAGE_FORBIDDEN_PATH_FILE_TEMPLATES;
        }
        return STATUS_PAGE_FORBIDDEN_PATH_FILE_TEMPLATES;
    }
}
