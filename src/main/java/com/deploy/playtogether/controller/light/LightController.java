package com.deploy.playtogether.controller.light;

import com.deploy.playtogether.common.dto.ApiResponse;
import com.deploy.playtogether.common.exception.SuccessCode;
import com.deploy.playtogether.controller.light.dto.request.LightRequestDto;
import com.deploy.playtogether.controller.light.dto.request.ReportLightRequestDto;
import com.deploy.playtogether.service.light.LightService;
import com.deploy.playtogether.service.light.S3Service;
import com.deploy.playtogether.service.light.dto.response.HotLightResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class LightController {
    private final LightService lightService;
    private final S3Service s3Service;

    @ApiOperation("[인증] 번개 생성 API")
    @PostMapping(value = "/light/add/{crewId}/{userId}", consumes = "multipart/form-data")
    public ApiResponse createLight(@PathVariable final Long userId, @PathVariable final Long crewId,
                                                     @ModelAttribute @Valid final LightRequestDto request){
        final List<String> imgPaths = s3Service.upload(request.getImages());
        lightService.createLight(userId, crewId, request.toServiceDto(), imgPaths);
        return ApiResponse.success(SuccessCode.LIGHT_ADD_SUCCESS);
    }

    @ApiOperation("[인증] 번개 신고 API")
    @PostMapping("/light/report/{crewId}/{lightId}/{userId}")
    public ApiResponse reportLight(@PathVariable final Long crewId, @PathVariable final Long lightId, @PathVariable final Long userId, @RequestBody @Valid final ReportLightRequestDto request){
        lightService.reportLight(crewId, lightId, userId, request.toServiceDto());
        return ApiResponse.success(SuccessCode.REPORT_LIGHT_SUCCESS);
    }

    @ApiOperation("인기 번개 조회 API")
    @GetMapping("/light/{crewId}/hot")
    public ApiResponse<List<HotLightResponse>> getHotLight(@PathVariable final Long crewId){
        return ApiResponse.success(SuccessCode.HOT_LIGHT_SUCCESS, lightService.getHotLight(crewId));
    }
}

