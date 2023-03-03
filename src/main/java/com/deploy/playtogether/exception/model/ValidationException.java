package com.deploy.playtogether.exception.model;

import com.deploy.playtogether.exception.ErrorCode;

public class ValidationException extends PlaytogetherException{
    public ValidationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ValidationException(String message) {
        super(message, ErrorCode.VALIDATION_EXCEPTION);
    }
}
