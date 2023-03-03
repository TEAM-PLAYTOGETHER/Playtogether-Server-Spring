package com.deploy.playtogether.crew.infrastructure;

import com.deploy.playtogether.crew.domain.CrewUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewUserRepository extends JpaRepository<CrewUser, Long> {
}
