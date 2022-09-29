package com.deploy.playtogether.controller.light;

import com.deploy.playtogether.common.dto.ApiResponse;
import com.deploy.playtogether.common.exception.SuccessCode;
import com.deploy.playtogether.controller.light.dto.request.LightRequestDto;
import com.deploy.playtogether.controller.light.dto.request.ReportLightDto;
import com.deploy.playtogether.service.light.LightService;
import com.deploy.playtogether.service.light.S3Service;
import com.deploy.playtogether.service.light.dto.response.LightResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class LightController {
    private final LightService lightService;
    private final S3Service s3Service;

    @ApiOperation("[인증] 번개 생성 API")
    @PostMapping(value = "/light/add/{crewId}/{userId}", consumes = "multipart/form-data")
    public ApiResponse<LightResponseDto> createLight(@PathVariable final Long userId, @PathVariable final Long crewId,
                                                     @ModelAttribute @Valid final LightRequestDto request){
        final List<String> imgPaths = s3Service.upload(request.getImages());
        return ApiResponse.success(SuccessCode.LIGHT_ADD_SUCCESS, lightService.createLight(userId, crewId, request.toServiceDto(), imgPaths));
    }

    @ApiOperation("[인증] 번개 신고 API")
    @PostMapping(value = "/light/report/{crewId}/{lightId}/{userId}")
    public ApiResponse reportLight(@PathVariable Long crewId, @PathVariable Long lightId, @PathVariable Long userId, @RequestBody @Valid ReportLightDto request){
        lightService.reportLight(crewId, lightId, userId, request.toServiceDto());
        return ApiResponse.success(SuccessCode.REPORT_LIGHT_SUCCESS);
    }
}

