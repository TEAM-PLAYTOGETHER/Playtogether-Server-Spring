package com.deploy.playtogether.light.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Getter
@Entity
@NoArgsConstructor
public class LightImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "light_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Light light;

    private LightImage(final String imgUrl, final Light light) {
        this.imgUrl = imgUrl;
        this.light = light;
    }
    public static LightImage newInstance(final String imgUrl, final Light light){
        return new LightImage(imgUrl, light);
    }
}
