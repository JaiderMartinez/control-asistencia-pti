package com.colegio.asistencia.exceptions;

public class PersonNotExistsException extends RuntimeException{

    public PersonNotExistsException(String message) {
        super(message);
    }
}
