package com.colegio.asistencia.constants;

public enum MessageEnum {

    MESSAGE_MODEL_ATTRIBUTE("message"),
    MESSAGE_ENVIRONMENTS_OF_PTI_EMPTY("No se encontro ningun ambiente"),
    MESSAGE_BAD_CREDENTIALS("");

    private final String message;

    MessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
