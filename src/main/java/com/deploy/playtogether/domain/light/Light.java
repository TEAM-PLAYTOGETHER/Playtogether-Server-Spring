package com.deploy.playtogether.domain.light;

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


import java.time.LocalDateTime;
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

    @Column(length = 20, nullable = false)
    private String place;

    @Column(nullable = false)
    private int peopleCnt;

    @Column(nullable = false)
    private String LightUrl;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private LocalTime time;

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

    public Light(Long id, String title, String place, int peopleCnt, String lightUrl, String description, LocalDateTime date, LocalTime time, LightCategory category, User user, Crew crew) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.peopleCnt = peopleCnt;
        this.LightUrl = lightUrl;
        this.description = description;
        this.date = date;
        this.time = time;
        this.category = category;
        this.user = user;
        this.crew = crew;
    }
}
