package com.deploy.playtogether.domain.crewUser.repository;

import com.deploy.playtogether.domain.crewUser.CrewUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewUserRepository extends JpaRepository<CrewUser, Long> {
}
