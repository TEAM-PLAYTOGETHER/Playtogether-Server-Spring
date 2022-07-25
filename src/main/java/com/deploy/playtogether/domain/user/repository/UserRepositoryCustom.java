package com.deploy.playtogether.domain.user.repository;

import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserSocialType;

public interface UserRepositoryCustom {
    boolean existsByName(String name);
    boolean existsBySocialIdAndSocialType(String socialId, UserSocialType socialType);
    //옵셔널 추가 요망
    User findUserBySocialIdAndSocialType(String socialId, UserSocialType socialType);
}
