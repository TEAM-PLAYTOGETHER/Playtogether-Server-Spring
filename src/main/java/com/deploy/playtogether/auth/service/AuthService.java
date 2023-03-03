package com.deploy.playtogether.auth.service;

import com.deploy.playtogether.auth.service.request.LoginDto;
import com.deploy.playtogether.auth.service.request.SignUpDto;

public interface AuthService {
    Long signUp(SignUpDto request);
    Long login(LoginDto loginDto);
}
