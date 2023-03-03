package com.deploy.playtogether.light.infrastructure;

import com.deploy.playtogether.light.domain.LightImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightImageRepository extends JpaRepository<LightImage, Long> {
}
