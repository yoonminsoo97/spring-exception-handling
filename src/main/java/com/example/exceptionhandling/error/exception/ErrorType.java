package com.example.exceptionhandling.error.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "E400001", "입력값이 잘못되었습니다."),
    ARG_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "E400002", "인자 유형이 일치하지 않습니다."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "E404001", "게시글이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    ErrorType(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

}
