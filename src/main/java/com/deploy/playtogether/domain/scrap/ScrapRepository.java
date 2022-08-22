package com.deploy.playtogether.domain.scrap;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    void deleteByLightIdAndUserId(Long lightId, Long userId);
    Optional<Scrap> findByLightIdAndUserId(Long lightId, Long userId);
}
