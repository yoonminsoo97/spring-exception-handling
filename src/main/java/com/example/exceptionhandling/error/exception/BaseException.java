package com.example.exceptionhandling.error.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorType errorType;

    public BaseException(ErrorType errorType) {
        this.errorType = errorType;
    }

}
