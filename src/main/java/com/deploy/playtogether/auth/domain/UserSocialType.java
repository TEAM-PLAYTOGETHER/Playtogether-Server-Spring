package com.deploy.playtogether.auth.domain;

import com.deploy.playtogether.util.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserSocialType implements EnumModel {
    KAKAO("카카오톡"),
    APPLE("애플"),
    GOOGLE("구글"),
    ;

    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
