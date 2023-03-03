package com.deploy.playtogether.light.domain;

import com.deploy.playtogether.util.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LightCategory implements EnumModel {
    EATING("먹을래"),
    DOING("할래"),
    GOING("갈래"),
    ;

    private final String value;

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}
