package com.deploy.playtogether.common.exception.model;

import com.deploy.playtogether.common.exception.ErrorCode;

public class UnAuthorizedException extends PlaytogetherException{
    public UnAuthorizedException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_EXCEPTION);
    }
}
