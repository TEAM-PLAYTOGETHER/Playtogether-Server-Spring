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
public class CrewResponse {
    private Long crewId;
    private String crewName;
    private String description;
    private String code;
    private Long masterId;

    public static CrewResponse of(Long crewId, String crewName, String description, String code, Long masterId){
        CrewResponse response = new CrewResponse(
                crewId,
                crewName,
                description,
                code,
                masterId
        );
        return response;
    }
}
