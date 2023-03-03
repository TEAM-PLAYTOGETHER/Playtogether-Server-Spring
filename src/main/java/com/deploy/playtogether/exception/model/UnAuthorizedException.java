package com.deploy.playtogether.exception.model;

import com.deploy.playtogether.exception.ErrorCode;

public class UnAuthorizedException extends PlaytogetherException{
    public UnAuthorizedException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_EXCEPTION);
    }
}
