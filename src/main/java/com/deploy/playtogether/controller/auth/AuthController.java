package com.deploy.playtogether.controller.auth;

import com.deploy.playtogether.common.dto.ApiResponse;
import com.deploy.playtogether.common.exception.SuccessCode;
import com.deploy.playtogether.controller.auth.dto.request.LoginRequestDto;
import com.deploy.playtogether.controller.auth.dto.request.SignUpRequestDto;
import com.deploy.playtogether.controller.auth.dto.response.LoginResponse;
import com.deploy.playtogether.service.auth.AuthService;
import com.deploy.playtogether.service.auth.AuthServiceProvider;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.deploy.playtogether.config.session.SessionConstants.USER_ID;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final HttpSession httpSession;
    private final AuthServiceProvider authServiceProvider;

    @ApiOperation("회원가입 페이지 - 회원가입을 요청합니다")
    @PostMapping("/v1/signup")
    public ApiResponse<LoginResponse> signUp(@Valid @RequestBody SignUpRequestDto request) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        Long userId = authService.signUp(request.toServiceDto());
        httpSession.setAttribute(USER_ID, userId);
        return ApiResponse.success(SuccessCode.SIGNUP_SUCCESS, LoginResponse.of(httpSession.getId(), userId));
    }

    @ApiOperation("로그인 페이지 - 로그인을 요청합니다")
    @PostMapping("/v1/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequestDto request) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        Long userId = authService.login(request.toServiceDto());
        httpSession.setAttribute(USER_ID, userId);
        return ApiResponse.success(SuccessCode.LOGIN_SUCCESS, LoginResponse.of(httpSession.getId(), userId));
    }
}
