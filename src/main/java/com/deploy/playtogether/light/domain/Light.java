package com.deploy.playtogether.light.domain;

import com.deploy.playtogether.common.domain.AuditingTimeEntity;
import com.deploy.playtogether.crew.domain.Crew;
import com.deploy.playtogether.auth.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Transient;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Light extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 20)
    private String place;

    @Column
    private int peopleCnt;

    @Transient
    private final List<LightImage> lightImageList = new ArrayList<>();

    @Column(length = 200, nullable = false)
    private String description;

    @Column
    private LocalDate date;

    @Column
    private LocalTime time;

    @Column
    private int scpCnt;

    @Column
    private int lightMemberCnt;

    //TODO 영어로 할건지, 먹갈할로 할건지.
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private LightCategory category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private Crew crew;

    @OneToMany(mappedBy = "light", cascade = CascadeType.ALL)
    private final List<LightUser> members = new ArrayList<>();

    @OneToMany(mappedBy = "light", cascade = CascadeType.ALL)
    private final List<ReportLight> reports = new ArrayList<>();

    private Light(final String title, final String place, final int peopleCnt, final String description, final LocalDate date, final LocalTime time, final int scpCnt, final int lightMemberCnt, final LightCategory category, final User user, final Crew crew) {
        this.title = title;
        this.place = place;
        this.peopleCnt = peopleCnt;
        this.description = description;
        this.date = date;
        this.time = time;
        this.lightMemberCnt = lightMemberCnt;
        this.scpCnt = scpCnt;
        this.category = category;
        this.user = user;
        this.crew = crew;
    }

    public static Light newInstance(final String title, final String place, final int peopleCnt, final String description, final LocalDate date, final LocalTime time, final LightCategory category, final User user, final Crew crew){
        return new Light(title, place, peopleCnt,  description, date, time, 0, 0, category, user, crew);
    }

    public void updateScpCnt(){
        this.scpCnt += 1;
    }
    public void updateMemberCnt(){
        this.lightMemberCnt += 1;
    }
}
