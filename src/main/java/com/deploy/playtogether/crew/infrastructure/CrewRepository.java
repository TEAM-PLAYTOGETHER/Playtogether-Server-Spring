package com.deploy.playtogether.crew.infrastructure;

import com.deploy.playtogether.crew.domain.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew, Long> {
}
