package com.deploy.playtogether.controller.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {
    private String sessionId;

    private Long userId;

    public static LoginResponse of(String sessionId, Long userId) {
        return new LoginResponse(sessionId, userId);
    }
}
