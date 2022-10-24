package com.deploy.playtogether.controller.light;

import com.deploy.playtogether.common.dto.ApiResponse;
import com.deploy.playtogether.common.exception.SuccessCode;
import com.deploy.playtogether.config.interceptor.Auth;
import com.deploy.playtogether.config.resolver.UserId;
import com.deploy.playtogether.controller.light.dto.request.LightRequestDto;
import com.deploy.playtogether.controller.light.dto.request.LightScrollRequest;
import com.deploy.playtogether.controller.light.dto.request.ReportLightRequestDto;
import com.deploy.playtogether.service.light.LightService;
import com.deploy.playtogether.service.light.S3Service;
import com.deploy.playtogether.service.light.dto.response.LightResponse;
import com.deploy.playtogether.service.light.dto.response.LightResponseUsingCursorResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LightController {
    private final LightService lightService;
    private final S3Service s3Service;

    @ApiOperation("[인증] 번개 생성 API")
    @Auth
    @PostMapping(value = "/light/add/{crewId}", consumes = "multipart/form-data")
    public ApiResponse createLight(@UserId Long userId, @PathVariable final Long crewId,
                                   @ModelAttribute @Valid final LightRequestDto request) {
        final List<String> imgPaths = s3Service.upload(request.getImages());
        lightService.createLight(userId, crewId, request.toServiceDto(), imgPaths);
        return ApiResponse.success(SuccessCode.LIGHT_ADD_SUCCESS);
    }

    @ApiOperation("[인증] 번개 신고 API")
    @Auth
    @PostMapping("/light/report/{crewId}/{lightId}")
    public ApiResponse reportLight(@PathVariable final Long crewId, @PathVariable final Long lightId, @UserId Long userId, @RequestBody @Valid final ReportLightRequestDto request) {
        lightService.reportLight(crewId, lightId, userId, request.toServiceDto());
        return ApiResponse.success(SuccessCode.REPORT_LIGHT_SUCCESS);
    }

    @ApiOperation("인기 번개 조회 API")
    @GetMapping("/light/{crewId}/hot")
    public ApiResponse<List<LightResponse>> getHotLight(@PathVariable final Long crewId) {
        return ApiResponse.success(SuccessCode.HOT_LIGHT_SUCCESS, lightService.getHotLight(crewId));
    }

    @ApiOperation("최신 번개 조회 API")
    @GetMapping("/light/{crewId}/new")
    public ApiResponse<List<LightResponse>> getNewLight(@PathVariable final Long crewId) {
        return ApiResponse.success(SuccessCode.NEW_LIGHT_SUCCESS, lightService.getNewLight(crewId));
    }

    @ApiOperation("내가 만든 번개 리스트 조회 API")
    @Auth
    @GetMapping("/light/{crewId}/open")
    public ApiResponse<LightResponseUsingCursorResponse> getOpenLight(@PathVariable final Long crewId, @UserId Long userId, @Valid final LightScrollRequest request) {
        return ApiResponse.success(SuccessCode.OPEN_LIGHT_SUCCESS, lightService.getOpenLight(crewId, userId, request.getSize(), request.getLastLightId()));
    }
}

