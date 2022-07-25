package com.deploy.playtogether.domain.user;

import com.deploy.playtogether.domain.user.repository.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
