package com.example.exceptionhandling.error.handler;

import com.example.exceptionhandling.error.dto.Error;
import com.example.exceptionhandling.error.exception.BaseException;
import com.example.exceptionhandling.error.exception.ErrorType;

import jakarta.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handle(BaseException ex, WebRequest request) {
        ErrorType errorType = ex.getErrorType();
        ProblemDetail problemDetail = createProblemDetail(ex, errorType, request);
        return ResponseEntity.status(errorType.getHttpStatus()).body(problemDetail);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handle(ConstraintViolationException ex, WebRequest request) {
        ErrorType errorType = ErrorType.INVALID_INPUT_VALUE;
        List<Error> fields = Error.fields(ex.getConstraintViolations());
        ProblemDetail problemDetail = createProblemDetail(ex, errorType, request);
        problemDetail.setProperty("errors", fields);
        return ResponseEntity.status(errorType.getHttpStatus()).body(problemDetail);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorType errorType = ErrorType.INVALID_INPUT_VALUE;
        List<Error> fields = Error.fields(ex.getFieldErrors());
        ProblemDetail problemDetail = createProblemDetail(ex, errorType, request);
        problemDetail.setProperty("errors", fields);
        return createResponseEntity(problemDetail, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorType errorType = ErrorType.ARG_TYPE_MISMATCH;
        Error error = Error.typeMismatch(ex);
        ProblemDetail problemDetail = createProblemDetail(ex, errorType, request);
        problemDetail.setProperty("errors", error);
        return createResponseEntity(problemDetail, headers, status, request);
    }

    private ProblemDetail createProblemDetail(Exception ex, ErrorType errorType, WebRequest request) {
        ProblemDetail problemDetail = createProblemDetail(ex, errorType.getHttpStatus(), errorType.getMessage(), null, null, request);
        problemDetail.setType(URI.create(""));
        problemDetail.setProperty("error_code", errorType.getErrorCode());
        return problemDetail;
    }

}
