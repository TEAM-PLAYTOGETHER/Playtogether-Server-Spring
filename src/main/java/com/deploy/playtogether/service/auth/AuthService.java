package com.deploy.playtogether.service.auth;

import com.deploy.playtogether.service.auth.dto.request.LoginDto;
import com.deploy.playtogether.service.auth.dto.request.SignUpDto;

public interface AuthService {
    Long signUp(SignUpDto request);
    Long login(LoginDto loginDto);
}
