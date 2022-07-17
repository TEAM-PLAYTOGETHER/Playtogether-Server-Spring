package com.deploy.playtogether.domain.user;

import com.deploy.playtogether.domain.common.AuditingTimeEntity;
import com.deploy.playtogether.domain.crewUser.CrewUser;
import com.deploy.playtogether.domain.lightUser.LightUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Embedded;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Embedded
    private SocialInfo socialInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CrewUser> crews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LightUser> lights = new ArrayList<>();

    //TODO 빌더 패턴 삭제 요망.
    @Builder(access = AccessLevel.PUBLIC)
    private User(String socialId, UserSocialType socialType, String nickname, String email){
        this.socialInfo = SocialInfo.of(socialId, socialType);
        this.nickname = nickname;
        this.email = email;
    }

    @NotNull
    public String getSocialId() {
        return this.socialInfo.getSocialId();
    }

    @NotNull
    public UserSocialType getSocialType() {
        return this.socialInfo.getSocialType();
    }
}
