package com.deploy.playtogether.exception.model;

import com.deploy.playtogether.exception.ErrorCode;

public class BadGatewayException extends PlaytogetherException{

    public BadGatewayException(String message) {
        super(message, ErrorCode.BAD_GATEWAY_EXCEPTION);
    }
}
