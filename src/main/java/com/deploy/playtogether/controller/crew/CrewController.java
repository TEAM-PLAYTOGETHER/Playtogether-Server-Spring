package com.deploy.playtogether.controller.crew;

import com.deploy.playtogether.common.dto.ApiResponse;
import com.deploy.playtogether.common.exception.SuccessCode;
import com.deploy.playtogether.config.interceptor.Auth;
import com.deploy.playtogether.config.resolver.UserId;
import com.deploy.playtogether.controller.crew.dto.request.CrewRequestDto;
import com.deploy.playtogether.service.crew.CrewService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class CrewController {

    private final CrewService crewService;

    @ApiOperation("[인증] 동아리 생성 API")
    @Auth
    @PostMapping("/crew")
    public ApiResponse createCrew(@UserId Long userId, @RequestBody @Valid final CrewRequestDto request){
        crewService.createCrew(userId, request.toServiceDto());
        return ApiResponse.success(SuccessCode.LIGHT_ADD_SUCCESS);
    }
}
