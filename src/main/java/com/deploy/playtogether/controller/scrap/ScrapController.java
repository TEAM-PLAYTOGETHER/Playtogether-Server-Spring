package com.deploy.playtogether.controller.scrap;

import com.deploy.playtogether.common.dto.ApiResponse;
import com.deploy.playtogether.common.exception.SuccessCode;
import com.deploy.playtogether.config.interceptor.Auth;
import com.deploy.playtogether.config.resolver.UserId;
import com.deploy.playtogether.service.scrap.ScrapService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ScrapController {

    private final ScrapService scrapService;

    @ApiOperation("[인증] 번개 찜 API")
    @Auth
    @PostMapping("/light/scrap/{lightId}")
    public ApiResponse addScrap(@PathVariable final Long lightId, @UserId Long userId){
        scrapService.addScrap(lightId, userId);
        return ApiResponse.success(SuccessCode.LIGHT_SCRAP_SUCCESS);
    }

    @ApiOperation("[인증] 번개 찜 취소 API")
    @Auth
    @DeleteMapping("/light/scrap/{lightId}/{userId}")
    public ApiResponse deleteScrap(@PathVariable final Long lightId, @UserId Long userId){
        scrapService.deleteScrap(lightId, userId);
        return ApiResponse.success(SuccessCode.LIGHT_DELETE_SCRAP_SUCCESS);
    }
}
