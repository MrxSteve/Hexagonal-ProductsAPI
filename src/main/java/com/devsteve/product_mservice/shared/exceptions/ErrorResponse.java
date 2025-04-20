package com.devsteve.product_mservice.shared.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final List<String> detalles;

    // Constructor sin detalles (para errores simples)
    public ErrorResponse(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.detalles = null; // null si no se usa
    }

    // Constructor para errores multiples (con detalles)
    public ErrorResponse(int status, String error, String message, String path, List<String> detalles) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.detalles = detalles;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public List<String> getDetalles() {
        return detalles;
    }
}
