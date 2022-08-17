package com.deploy.playtogether.domain.light;

import com.deploy.playtogether.common.util.ListToStringConverter;
import com.deploy.playtogether.domain.common.AuditingTimeEntity;
import com.deploy.playtogether.domain.crew.Crew;
import com.deploy.playtogether.domain.lightUser.LightUser;
import com.deploy.playtogether.domain.user.User;
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
import javax.persistence.Convert;


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

    @Column(length = 1000)
    @Convert(converter = ListToStringConverter.class)
    private List<String> imageUrls = new ArrayList<>();

    @Column(length = 200, nullable = false)
    private String description;

    @Column
    private LocalDate date;

    @Column
    private LocalTime time;

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
    private List<LightUser> members = new ArrayList<>();

    public Light(String title, String place, int peopleCnt, List<String> imageUrls, String description, LocalDate date, LocalTime time, LightCategory category, User user, Crew crew) {
        this.title = title;
        this.place = place;
        this.peopleCnt = peopleCnt;
        this.imageUrls = imageUrls;
        this.description = description;
        this.date = date;
        this.time = time;
        this.category = category;
        this.user = user;
        this.crew = crew;
    }

    public static Light newInstance(String title, String place, int peopleCnt, List<String> imageUrls, String description, LocalDate date, LocalTime time, LightCategory category, User user, Crew crew){
        return new Light(title, place, peopleCnt, imageUrls, description, date, time, category, user, crew);
    }
}
