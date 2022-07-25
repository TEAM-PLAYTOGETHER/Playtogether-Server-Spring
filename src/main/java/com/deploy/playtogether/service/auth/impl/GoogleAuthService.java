package com.deploy.playtogether.service.auth.impl;

import com.deploy.playtogether.common.util.HttpHeaderUtils;
import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.domain.user.UserSocialType;
import com.deploy.playtogether.external.client.google.GoogleAuthApiClient;
import com.deploy.playtogether.external.client.google.dto.response.GoogleProfileInfoResponse;
import com.deploy.playtogether.service.auth.AuthService;
import com.deploy.playtogether.service.auth.dto.request.LoginDto;
import com.deploy.playtogether.service.auth.dto.request.SignUpDto;
import com.deploy.playtogether.service.user.UserService;
import com.deploy.playtogether.service.user.UserServiceUtils;
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
