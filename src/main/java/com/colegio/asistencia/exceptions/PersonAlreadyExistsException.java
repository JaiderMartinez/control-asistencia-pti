package com.colegio.asistencia.exceptions;

public class PersonAlreadyExistsException extends RuntimeException{

    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
