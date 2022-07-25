package com.deploy.playtogether.service.auth;

import com.deploy.playtogether.service.auth.dto.request.SignUpDto;

public interface AuthService {
    Long signUp(SignUpDto request);
}
