package com.deploy.playtogether.scrap.domain;

import com.deploy.playtogether.common.domain.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scrap extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long lightId;

    @Column(nullable = false)
    private Long userId;

    private Scrap(Long lightId, Long userId) {
        this.lightId = lightId;
        this.userId = userId;
    }

    public static Scrap of(Long lightId, Long userId) {
        return new Scrap(lightId, userId);
    }

}
