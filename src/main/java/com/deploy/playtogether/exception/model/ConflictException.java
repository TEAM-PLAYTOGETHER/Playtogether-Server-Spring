package com.deploy.playtogether.exception.model;

import com.deploy.playtogether.exception.ErrorCode;

public class ConflictException extends PlaytogetherException{
    public ConflictException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ConflictException(String message) {
        super(message, ErrorCode.CONFLICT_EXCEPTION);
    }
}
