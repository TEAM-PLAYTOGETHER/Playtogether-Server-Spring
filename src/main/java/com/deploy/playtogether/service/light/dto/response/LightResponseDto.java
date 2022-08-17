package com.deploy.playtogether.service.light.dto.response;


import com.deploy.playtogether.domain.light.LightCategory;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LightResponseDto {

    private Long lightId;
    private LightCategory category;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private List<String> lightImages;
    private String place;
    private int peopleCount;
    private String description;
    private Long organizerId;
    private Long crewId;

    public static LightResponseDto of(Long lightId, LightCategory category, String title, LocalDate date, LocalTime time, List<String> lightImages, String place, int peopleCount, String description, Long organizerId, Long crewId){
        LightResponseDto response = new LightResponseDto(
                lightId,
                category,
                title,
                date,
                time,
                lightImages,
                place,
                peopleCount,
                description,
                organizerId,
                crewId
        );
        return response;
    }
}
