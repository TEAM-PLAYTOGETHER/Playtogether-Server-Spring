package com.deploy.playtogether.service.light.dto.request;

import com.deploy.playtogether.domain.light.LightCategory;
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
public class LightDto {
    @NotNull
    private LightCategory category;
    @NotNull
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String place;
    private List<MultipartFile> images;
    private int peopleCount;
    @NotNull
    private String description;

    public static LightDto of (@NotNull LightCategory category, @NotNull String title, LocalDate date, LocalTime time, String place, List<MultipartFile> images, int peopleCount, @NotNull String description){
        return new LightDto(category, title, date, time, place, images, peopleCount, description);
    }
}
