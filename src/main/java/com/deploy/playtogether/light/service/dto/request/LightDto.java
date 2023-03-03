package com.deploy.playtogether.light.service.dto.request;

import com.deploy.playtogether.light.domain.LightCategory;
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

    public static LightDto of(@NotNull final LightCategory category, @NotNull final String title, final LocalDate date,
                              final LocalTime time, final String place, final List<MultipartFile> images, final int peopleCount,
                              @NotNull final String description) {
        return new LightDto(category, title, date, time, place, images, peopleCount, description);
    }
}
