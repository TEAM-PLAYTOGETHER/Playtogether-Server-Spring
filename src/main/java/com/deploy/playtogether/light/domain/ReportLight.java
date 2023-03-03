package com.deploy.playtogether.light.domain;

import com.deploy.playtogether.common.domain.AuditingTimeEntity;
import com.deploy.playtogether.auth.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportLight extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 100)
    private String reportReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "light_id")
    private Light light;

    private ReportLight(final User user, final String reportReason, final Light light) {
        this.user = user;
        this.reportReason = reportReason;
        this.light = light;
    }

    public static ReportLight newInstance(final User user, final String reportReason, final Light light){
        return new ReportLight(user, reportReason, light);
    }

}
