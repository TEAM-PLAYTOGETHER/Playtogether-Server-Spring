package com.deploy.playtogether.domain.room;

import com.deploy.playtogether.domain.common.AuditingTimeEntity;
import com.deploy.playtogether.domain.message.Message;
import com.deploy.playtogether.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_one_id")
    private User userOne;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_two_id")
    private User userTwo;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();
}
