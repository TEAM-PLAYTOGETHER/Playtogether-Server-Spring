package com.deploy.playtogether.common.exception.model;

import com.deploy.playtogether.common.exception.ErrorCode;

public class BadGatewayException extends PlaytogetherException{

    public BadGatewayException(String message) {
        super(message, ErrorCode.BAD_GATEWAY_EXCEPTION);
    }
}
