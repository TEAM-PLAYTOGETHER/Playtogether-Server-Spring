package com.deploy.playtogether.auth.service.impl;

import com.deploy.playtogether.auth.infrastructure.UserRepository;
import com.deploy.playtogether.auth.domain.UserSocialType;
import com.deploy.playtogether.external.client.apple.AppleTokenDecoder;
import com.deploy.playtogether.auth.service.AuthService;
import com.deploy.playtogether.auth.service.request.LoginDto;
import com.deploy.playtogether.auth.service.request.SignUpDto;
import com.deploy.playtogether.auth.service.UserService;
import com.deploy.playtogether.auth.service.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppleAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.APPLE;
    private final UserRepository userRepository;

    private final AppleTokenDecoder appleTokenDecoder;

    private final UserService userService;
    @Override
    public Long signUp(SignUpDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        return userService.registerUser(request.toCreateUserDto(socialId));
    }

    @Override
    public Long login(LoginDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        return UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, socialId, socialType).getId();
    }
}

