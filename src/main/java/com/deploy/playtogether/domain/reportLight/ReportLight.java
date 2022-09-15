package com.deploy.playtogether.domain.reportLight;

import com.deploy.playtogether.domain.common.AuditingTimeEntity;
import com.deploy.playtogether.domain.light.Light;
import com.deploy.playtogether.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "light_id")
    private Light light;

    private ReportLight(final User user, final Light light) {
        this.user = user;
        this.light = light;
    }
    public static ReportLight newInstance(final User user, final Light light){
        return new ReportLight(user, light);
    }

}
