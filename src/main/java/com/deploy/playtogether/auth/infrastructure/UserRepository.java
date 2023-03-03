package com.deploy.playtogether.auth.infrastructure;

import com.deploy.playtogether.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
