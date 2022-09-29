package com.deploy.playtogether.controller.light.dto.request;

import com.deploy.playtogether.service.light.dto.request.ReportDto;
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
public class ReportLightDto {

    @NotNull
    private String reportReason;

    public ReportDto toServiceDto(){
        return ReportDto.of(reportReason);
    }
}
