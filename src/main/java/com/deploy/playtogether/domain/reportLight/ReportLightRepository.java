package com.deploy.playtogether.domain.reportLight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportLightRepository extends JpaRepository<ReportLight, Long> {
    Optional<ReportLight> findByUserIdAndLightId(Long id, Long id1);
}
