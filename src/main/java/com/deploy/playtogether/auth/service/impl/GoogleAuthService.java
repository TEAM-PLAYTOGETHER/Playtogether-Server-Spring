package com.deploy.playtogether.auth.service.impl;

import com.deploy.playtogether.util.HttpHeaderUtils;
import com.deploy.playtogether.auth.infrastructure.UserRepository;
import com.deploy.playtogether.auth.domain.UserSocialType;
import com.deploy.playtogether.external.client.google.GoogleAuthApiClient;
import com.deploy.playtogether.external.client.google.dto.response.GoogleProfileInfoResponse;
import com.deploy.playtogether.auth.service.AuthService;
import com.deploy.playtogether.auth.service.request.LoginDto;
import com.deploy.playtogether.auth.service.request.SignUpDto;
import com.deploy.playtogether.auth.service.UserService;
import com.deploy.playtogether.auth.service.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoogleAuthService implements AuthService {

    private final UserSocialType socialType = UserSocialType.GOOGLE;
    private final UserRepository userRepository;

    private final GoogleAuthApiClient googleAuthApiCaller;

    private final UserService userService;

    @Override
    public Long signUp(SignUpDto request) {
        GoogleProfileInfoResponse response = googleAuthApiCaller.getProfileInfo((HttpHeaderUtils.withBearerToken(request.getToken())));
        return userService.registerUser(request.toCreateUserDto(response.getId()));
    }

    @Override
    public Long login(LoginDto request) {
        GoogleProfileInfoResponse response = googleAuthApiCaller.getProfileInfo((HttpHeaderUtils.withBearerToken(request.getToken())));
        return UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), socialType).getId();
    }
}
