package com.deploy.playtogether.domain.crew;

import com.deploy.playtogether.domain.common.AuditingTimeEntity;
import com.deploy.playtogether.domain.crewUser.CrewUser;
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
    private List<CrewUser> members = new ArrayList<>();

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String code;


}
