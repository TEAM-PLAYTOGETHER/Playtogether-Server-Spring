package com.deploy.playtogether.domain.crew.repository;

import com.deploy.playtogether.domain.crew.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew, Long> {
}
