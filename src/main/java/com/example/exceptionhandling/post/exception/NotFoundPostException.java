package com.example.exceptionhandling.post.exception;

import com.example.exceptionhandling.error.exception.BaseException;
import com.example.exceptionhandling.error.exception.ErrorType;

public class NotFoundPostException extends BaseException {

    public NotFoundPostException() {
        super(ErrorType.NOT_FOUND_POST);
    }

}
