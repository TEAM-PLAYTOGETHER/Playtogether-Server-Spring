package com.deploy.playtogether.domain.light.repository;

import com.deploy.playtogether.domain.light.Light;

import java.util.List;

public interface LightRepositoryCustom {
    List<Light> findAllByUserIdUsingCursor(Long userId, int size, Long lastLightId);
}
