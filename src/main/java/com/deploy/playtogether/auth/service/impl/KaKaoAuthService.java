package com.deploy.playtogether.auth.service.impl;

import com.deploy.playtogether.util.HttpHeaderUtils;
import com.deploy.playtogether.auth.infrastructure.UserRepository;
import com.deploy.playtogether.auth.domain.UserSocialType;
import com.deploy.playtogether.external.client.kakao.KaKaoAuthApiClient;
import com.deploy.playtogether.external.client.kakao.dto.response.KaKaoProfileResponse;
import com.deploy.playtogether.auth.service.AuthService;
import com.deploy.playtogether.auth.service.request.LoginDto;
import com.deploy.playtogether.auth.service.request.SignUpDto;
import com.deploy.playtogether.auth.service.UserService;
import com.deploy.playtogether.auth.service.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KaKaoAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.KAKAO;
    private final UserRepository userRepository;

    private final KaKaoAuthApiClient kakaoAuthApiCaller;

    private final UserService userService;
    @Override
    public Long signUp(SignUpDto request) {
        KaKaoProfileResponse response = kakaoAuthApiCaller.getProfileInfo(HttpHeaderUtils.withBearerToken(request.getToken()));
        return userService.registerUser(request.toCreateUserDto(response.getId()));
    }

    @Override
    public Long login(LoginDto request) {
        KaKaoProfileResponse response = kakaoAuthApiCaller.getProfileInfo(HttpHeaderUtils.withBearerToken(request.getToken()));
        return UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), socialType).getId();
    }
}
