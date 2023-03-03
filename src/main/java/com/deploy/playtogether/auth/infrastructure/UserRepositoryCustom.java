package com.deploy.playtogether.auth.infrastructure;

import com.deploy.playtogether.auth.domain.User;
import com.deploy.playtogether.auth.domain.UserSocialType;

public interface UserRepositoryCustom {
    boolean existsByName(String name);
    boolean existsBySocialIdAndSocialType(String socialId, UserSocialType socialType);
    User findUserById(Long id);
    //옵셔널 추가 요망
    User findUserBySocialIdAndSocialType(String socialId, UserSocialType socialType);
}
