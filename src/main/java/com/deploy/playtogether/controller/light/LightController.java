package com.deploy.playtogether.controller.light;

import com.deploy.playtogether.common.dto.ApiResponse;
import com.deploy.playtogether.common.exception.SuccessCode;
import com.deploy.playtogether.controller.light.dto.request.LightRequestDto;
import com.deploy.playtogether.service.light.LightService;
import com.deploy.playtogether.service.light.dto.response.LightResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class LightController {
    private final LightService lightService;

    @ApiOperation("[인증] 번개 생성 API")
    @PostMapping("/light/add/{crewId}/{userId}")
    public ApiResponse<LightResponseDto> createLight(@PathVariable Long userId, @PathVariable Long crewId, @RequestBody @Valid LightRequestDto request){
        return ApiResponse.success(SuccessCode.LIGHT_ADD_SUCCESS, lightService.createLight(userId, crewId, request.toServiceDto()));
    }
}

