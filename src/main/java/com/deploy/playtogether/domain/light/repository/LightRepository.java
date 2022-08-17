package com.deploy.playtogether.domain.light.repository;

import com.deploy.playtogether.domain.light.Light;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightRepository extends JpaRepository<Light, Long> {
}
