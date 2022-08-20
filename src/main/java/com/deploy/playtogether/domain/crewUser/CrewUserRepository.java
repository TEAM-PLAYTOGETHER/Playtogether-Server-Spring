package com.deploy.playtogether.domain.crewUser;

import com.deploy.playtogether.domain.crewUser.CrewUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewUserRepository extends JpaRepository<CrewUser, Long> {
}
