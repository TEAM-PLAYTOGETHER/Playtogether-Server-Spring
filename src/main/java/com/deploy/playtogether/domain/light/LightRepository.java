package com.deploy.playtogether.domain.light;

import com.deploy.playtogether.domain.light.repository.LightRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LightRepository extends JpaRepository<Light, Long>, LightRepositoryCustom {
    List<Light> findAllByOrderByScpCntDesc(Pageable pageable);

    List<Light> findAllByOrderByCreatedAtDesc(Pageable page);
}
