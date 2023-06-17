package com.colegio.asistencia.exceptions;

public class WrongPasswordStructureException extends RuntimeException{

    public WrongPasswordStructureException(String message) {
        super(message);
    }
}
