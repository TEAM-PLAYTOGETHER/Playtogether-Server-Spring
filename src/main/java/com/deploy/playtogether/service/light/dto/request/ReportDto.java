package com.deploy.playtogether.service.light.dto.request;

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
public class ReportDto {

    @NotNull
    private String reportReason;

    public static ReportDto of(@NotNull final String reportReason) {
        return new ReportDto(reportReason);
    }
}
