package com.deploy.playtogether.controller.light.dto.request;

import com.deploy.playtogether.domain.light.LightCategory;
import com.deploy.playtogether.service.light.dto.request.LightDto;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LightRequestDto {

    @NotNull
    private LightCategory category;
    @NotNull
    private String title;
    private LocalDate date;
    private LocalTime time;
    private List<String> lightImages;
    private String place;
    private int peopleCount;
    @NotNull
    private String description;

    public LightDto toServiceDto(){
        return LightDto.of(category, title, date, time, lightImages, place, peopleCount, description);
    }

}
