package com.deploy.playtogether.domain.lightUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LightUserRepository extends JpaRepository<LightUser, Long>{
    void deleteByLightIdAndUserId(Long lightId, Long userId);
    Optional<LightUser> findByUserIdAndLightId(Long userId, Long lightId);
}
