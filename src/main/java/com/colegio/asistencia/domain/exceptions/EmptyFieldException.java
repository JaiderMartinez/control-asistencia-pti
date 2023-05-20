package com.colegio.asistencia.domain.exceptions;

public class EmptyFieldException extends RuntimeException{

    public EmptyFieldException(String message) {
        super(message);
    }
}
