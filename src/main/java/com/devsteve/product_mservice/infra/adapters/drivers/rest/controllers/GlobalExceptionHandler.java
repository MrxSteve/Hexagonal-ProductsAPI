package com.devsteve.product_mservice.infra.adapters.drivers.rest.controllers;

import com.devsteve.product_mservice.shared.exceptions.DuplicateResourceException;
import com.devsteve.product_mservice.shared.exceptions.ErrorResponse;
import com.devsteve.product_mservice.shared.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateResourceException ex, HttpServletRequest request) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        String mensaje = String.join("; ", errores);
        return buildError(HttpStatus.BAD_REQUEST, mensaje, request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado: " + ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFound(NoHandlerFoundException ex, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, "Ruta no encontrada: " + request.getRequestURI(), request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> buildError(HttpStatus status, String mensaje, String path) {
        ErrorResponse error = new ErrorResponse(status.value(), status.getReasonPhrase(), mensaje, path);
        return ResponseEntity.status(status).body(error);
    }
}
