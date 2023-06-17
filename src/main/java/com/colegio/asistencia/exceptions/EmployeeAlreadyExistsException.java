package com.colegio.asistencia.exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException{

    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
