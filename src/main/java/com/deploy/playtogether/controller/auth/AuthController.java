package com.deploy.playtogether.controller.auth;

import com.deploy.playtogether.common.dto.ApiResponse;
import com.deploy.playtogether.common.exception.SuccessCode;
import com.deploy.playtogether.config.jwt.JwtService;
import com.deploy.playtogether.controller.auth.dto.request.LoginRequestDto;
import com.deploy.playtogether.controller.auth.dto.request.SignUpRequestDto;
import com.deploy.playtogether.controller.auth.dto.response.LoginResponse;
import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.service.auth.AuthService;
import com.deploy.playtogether.service.auth.AuthServiceProvider;
import com.deploy.playtogether.service.user.UserService;
import com.deploy.playtogether.service.user.UserServiceUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final AuthServiceProvider authServiceProvider;
    private final JwtService jwtService;

    @ApiOperation("회원가입 페이지 - 회원가입을 요청합니다")
    @PostMapping("/v1/signup")
    public ApiResponse<LoginResponse> signUp(@Valid @RequestBody final SignUpRequestDto request) {
        final AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        final Long userId = authService.signUp(request.toServiceDto());
        final String token = jwtService.issuedToken(String.valueOf(userId), "USER", 60 * 60 * 24 * 30L);
        return ApiResponse.success(SuccessCode.SIGNUP_SUCCESS, LoginResponse.of(token, userId));
    }

    @ApiOperation("로그인 페이지 - 로그인을 요청합니다")
    @PostMapping("/v1/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody final LoginRequestDto request) {
        final AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        final Long userId = authService.login(request.toServiceDto());
        final String token = jwtService.issuedToken(String.valueOf(userId), "USER", 60 * 60 * 24 * 30L);

        User findUser = UserServiceUtils.findUserById(userRepository, userId);
        return ApiResponse.success(SuccessCode.LOGIN_SUCCESS, LoginResponse.of(token, userId));
    }
}
