package com.deploy.playtogether.light.infrastructure;

import com.deploy.playtogether.light.domain.ReportLight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportLightRepository extends JpaRepository<ReportLight, Long> {
    Optional<ReportLight> findByUserIdAndLightId(Long id, Long id1);
}
