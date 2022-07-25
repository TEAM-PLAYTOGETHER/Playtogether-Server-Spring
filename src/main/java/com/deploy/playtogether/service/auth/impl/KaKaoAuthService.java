package com.deploy.playtogether.service.auth.impl;

import com.deploy.playtogether.common.util.HttpHeaderUtils;
import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.domain.user.UserSocialType;
import com.deploy.playtogether.external.client.kakao.KaKaoAuthApiClient;
import com.deploy.playtogether.external.client.kakao.dto.response.KaKaoProfileResponse;
import com.deploy.playtogether.service.auth.AuthService;
import com.deploy.playtogether.service.auth.dto.request.LoginDto;
import com.deploy.playtogether.service.auth.dto.request.SignUpDto;
import com.deploy.playtogether.service.user.UserService;
import com.deploy.playtogether.service.user.UserServiceUtils;
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
