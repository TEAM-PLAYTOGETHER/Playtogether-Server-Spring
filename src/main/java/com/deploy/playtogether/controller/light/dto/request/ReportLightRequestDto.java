package com.deploy.playtogether.controller.light.dto.request;

import com.deploy.playtogether.service.light.dto.request.ReportLightDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportLightRequestDto {

    @NotNull
    private String reportReason;

    public ReportLightDto toServiceDto(){
        return ReportLightDto.of(reportReason);
    }
}
