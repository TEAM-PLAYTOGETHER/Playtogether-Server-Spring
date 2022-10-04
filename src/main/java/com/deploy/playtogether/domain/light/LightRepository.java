package com.deploy.playtogether.domain.light;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LightRepository extends JpaRepository<Light, Long>{
    List<Light> findAllByOrderByScpCntDesc(Pageable pageable);
}
