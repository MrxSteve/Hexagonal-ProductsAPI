package com.devsteve.product_mservice.shared.exceptions;

import java.util.List;

public class MultipleErrorsException extends RuntimeException {
    private final List<String> errores;

    public MultipleErrorsException(List<String> errores) {
        super("Mulltiples errores encontrados");
        this.errores = errores;
    }

    public List<String> getErrores() {
        return errores;
    }
}
