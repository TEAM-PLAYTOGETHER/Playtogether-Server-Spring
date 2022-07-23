package com.deploy.playtogether.common.exception.model;

import com.deploy.playtogether.common.exception.ErrorCode;

public class NotFoundException extends PlaytogetherException{
    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundException(String message) {
        super(message, ErrorCode.NOT_FOUND_EXCEPTION);
    }
}
