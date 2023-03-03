package com.deploy.playtogether.light.service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LightResponseUsingCursorResponse {
    private List<LightResponse> response;
    private Long nextLightId;

    public static LightResponseUsingCursorResponse of(final List<LightResponse> lightResponse, final Long nextLightId){
        LightResponseUsingCursorResponse response = new LightResponseUsingCursorResponse(
                lightResponse,
                nextLightId
        );
        return response;
    }
}
