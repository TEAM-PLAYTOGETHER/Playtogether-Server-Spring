package com.deploy.playtogether.light.infrastructure;

import com.deploy.playtogether.light.domain.Light;
import com.deploy.playtogether.light.infrastructure.LightRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LightRepository extends JpaRepository<Light, Long>, LightRepositoryCustom {
    List<Light> findAllByOrderByScpCntDesc(Pageable pageable);

    List<Light> findAllByOrderByCreatedAtDesc(Pageable page);
}
