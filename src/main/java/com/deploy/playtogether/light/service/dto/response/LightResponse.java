package com.deploy.playtogether.light.service.dto.response;

import com.deploy.playtogether.light.domain.LightCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LightResponse {

    private Long lightId;
    private LightCategory category;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String place;
    private int peopleCount;
    private int ScpCnt;
    private int LightMemberCount;

    private LightResponse(final Long lightId, final LightCategory category, final String title, final LocalDate date, final LocalTime time, final String place, final int peopleCount, final int scpCnt, final int lightMemberCount) {
        this.lightId = lightId;
        this.category = category;
        this.title = title;
        this.date = date;
        this.time = time;
        this.place = place;
        this.peopleCount = peopleCount;
        this.ScpCnt = scpCnt;
        this.LightMemberCount = lightMemberCount;
    }
    public static LightResponse of(final Long lightId, final LightCategory category, final String title, final LocalDate date, final LocalTime time, final String place, final int peopleCount, final int scpCnt, final int lightMemberCount){
        LightResponse response = new LightResponse(
                lightId,
                category,
                title,
                date,
                time,
                place,
                peopleCount,
                scpCnt,
                lightMemberCount
        );
        return response;
    }
}
