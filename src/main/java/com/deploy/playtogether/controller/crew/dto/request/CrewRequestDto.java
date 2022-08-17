package com.deploy.playtogether.controller.crew.dto.request;

import com.deploy.playtogether.service.crew.dto.request.CrewDto;
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
public class CrewRequestDto {

    @NotNull
    private String crewName;
    @NotNull
    private String description;

    public CrewDto toServiceDto(){
        return CrewDto.of(crewName, description);
    }
}
