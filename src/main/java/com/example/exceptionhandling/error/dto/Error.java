package com.example.exceptionhandling.error.dto;

import jakarta.validation.ConstraintViolation;

import lombok.Getter;

import org.springframework.beans.TypeMismatchException;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Error {

    private String field;
    private String input;
    private String message;

    private Error(String field, String input, String message) {
        this.field = field;
        this.input = input;
        this.message = message;
    }

    public static List<Error> fields(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(fieldError -> new Error(
                        fieldError.getField(),
                        fieldError.getRejectedValue() == null ? "" : fieldError.getRejectedValue().toString(),
                        fieldError.getDefaultMessage()
                )).collect(Collectors.toList());
    }

    public static List<Error> fields(Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
                .map(constraint -> new Error(
                        constraint.getPropertyPath().toString().split("\\.")[1],
                        constraint.getInvalidValue().toString(),
                        constraint.getMessage()
                )).collect(Collectors.toList());
    }

    public static Error typeMismatch(TypeMismatchException ex) {
        return new Error(ex.getPropertyName(), String.valueOf(ex.getValue()), ex.getLocalizedMessage());
    }

}
