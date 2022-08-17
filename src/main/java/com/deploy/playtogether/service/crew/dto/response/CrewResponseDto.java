package com.deploy.playtogether.service.crew.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CrewResponseDto {
    private Long crewId;
    private String crewName;
    private String description;
    private String code;
    private Long masterId;

    public static CrewResponseDto of(Long crewId, String crewName, String description, String code, Long masterId){
        CrewResponseDto response = new CrewResponseDto(
                crewId,
                crewName,
                description,
                code,
                masterId
        );
        return response;
    }
}
