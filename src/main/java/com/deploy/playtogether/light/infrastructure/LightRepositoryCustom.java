package com.deploy.playtogether.light.infrastructure;

import com.deploy.playtogether.light.domain.Light;

import java.util.List;

public interface LightRepositoryCustom {
    List<Light> findAllByUserIdUsingCursor(Long userId, int size, Long lastLightId);
}
