package com.deploy.playtogether.controller.lightUser;

import com.deploy.playtogether.common.dto.ApiResponse;
import com.deploy.playtogether.common.exception.SuccessCode;
import com.deploy.playtogether.config.interceptor.Auth;
import com.deploy.playtogether.config.resolver.UserId;
import com.deploy.playtogether.service.lightUser.LightUserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LightUserController {

    private final LightUserService lightUserService;

    @ApiOperation("[인증] 번개 참여 API")
    @Auth
    @PostMapping("/light/enter/{lightId}")
    public ApiResponse enterLight(@PathVariable final Long lightId, @UserId Long userId){
        lightUserService.enterLight(lightId, userId);
        return ApiResponse.success(SuccessCode.ENTER_LIGHT_SUCCESS);
    }
    @ApiOperation("[인증] 번개 참여 취소 API")
    @Auth
    @DeleteMapping("/light/enter/{lightId}")
    public ApiResponse outLight(@PathVariable final Long lightId, @UserId Long userId){
        lightUserService.outLight(lightId, userId);
        return ApiResponse.success(SuccessCode.OUT_LIGHT_SUCCESS);
    }
}
