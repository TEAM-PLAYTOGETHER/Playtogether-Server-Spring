package com.deploy.playtogether.crew.service.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CrewDto {
    @NotNull
    private String crewName;
    @NotNull
    private String description;

    public static CrewDto of (String crewName, String description){
        return new CrewDto(crewName, description);
    }

}
