package com.deploy.playtogether.domain.lightUser.repository;

import com.deploy.playtogether.domain.lightUser.LightUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightUserRepository extends JpaRepository<LightUser, Long> {
}
