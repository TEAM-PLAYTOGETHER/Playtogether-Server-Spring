package com.deploy.playtogether.service.auth.impl;

import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.domain.user.UserSocialType;
import com.deploy.playtogether.external.client.apple.AppleTokenDecoder;
import com.deploy.playtogether.service.auth.AuthService;
import com.deploy.playtogether.service.auth.dto.request.LoginDto;
import com.deploy.playtogether.service.auth.dto.request.SignUpDto;
import com.deploy.playtogether.service.user.UserService;
import com.deploy.playtogether.service.user.UserServiceUtils;
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

