package com.deploy.playtogether.light.controller.request;

import com.deploy.playtogether.light.domain.LightCategory;
import com.deploy.playtogether.light.service.dto.request.LightDto;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

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
    private String place;
    private int peopleCount;
    @NotNull
    private String description;
    private List<MultipartFile> images;

    public LightDto toServiceDto(){
        return LightDto.of(category, title, date, time, place, images, peopleCount, description);
    }

}
