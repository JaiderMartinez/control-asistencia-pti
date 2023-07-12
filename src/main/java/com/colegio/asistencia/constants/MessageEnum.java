package com.colegio.asistencia.constants;

public enum MessageEnum {

    MESSAGE_MODEL_ATTRIBUTE_FAILED("message"),
    MESSAGE_ENVIRONMENTS_OF_PTI_EMPTY("No se encontro ningun ambiente"),
    MESSAGE_BAD_CREDENTIALS("Malas Credenciales"),
    MESSAGE_MODEL_ATTRIBUTE_SUCCESS("successfulMessage"),
    MESSAGE_REPORT_GENERATED_SUCCESSFULLY("Archivo generado exitosamente");

    private final String message;

    MessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
