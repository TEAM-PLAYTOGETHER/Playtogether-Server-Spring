package com.deploy.playtogether.controller.light.dto.request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LightScrollRequest {
    @Min(value = 1, message = "{common.size.min}")
    @Max(value = 100, message = "{common.size.max}")
    private int size;

    @Nullable
    private Long lastLightId;
}
