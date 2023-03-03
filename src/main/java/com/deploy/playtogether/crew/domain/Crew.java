package com.deploy.playtogether.crew.domain;

import com.deploy.playtogether.common.domain.AuditingTimeEntity;
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



import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crew extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 200, nullable = false)
    private String description;

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL)
    private final List<CrewUser> members = new ArrayList<>();

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 6, nullable = false)
    private String code;

    private Crew(final User user, final String description, final String name, final String code) {
        this.user = user;
        this.description = description;
        this.name = name;
        this.code = code;
    }
    public static Crew newInstance(final User user, final String description, final String name, final String code){
        return new Crew(user, description, name, code);
    }

}
