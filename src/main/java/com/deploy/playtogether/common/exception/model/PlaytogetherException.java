package com.deploy.playtogether.common.exception.model;

import com.deploy.playtogether.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PlaytogetherException extends RuntimeException{
    private final ErrorCode errorCode;

    public PlaytogetherException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }
}
