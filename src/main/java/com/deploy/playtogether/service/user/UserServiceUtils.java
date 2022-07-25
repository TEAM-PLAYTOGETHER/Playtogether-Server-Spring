package com.deploy.playtogether.service.user;

import com.deploy.playtogether.common.exception.ErrorCode;
import com.deploy.playtogether.common.exception.model.ConflictException;
import com.deploy.playtogether.common.exception.model.NotFoundException;
import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.domain.user.UserSocialType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.deploy.playtogether.common.exception.ErrorCode.CONFLICT_NICKNAME_EXCEPTION;
import static com.deploy.playtogether.common.exception.ErrorCode.NOT_FOUND_USER_EXCEPTION;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceUtils {
    static void validateNotExistsUserName(UserRepository userRepository, String name) {
        if (userRepository.existsByName(name)) {
            throw new ConflictException(String.format("이미 존재하는 닉네임 (%s) 입니다", name), CONFLICT_NICKNAME_EXCEPTION);
        }
    }

    static void validateNotExistsUser(UserRepository userRepository, String socialId, UserSocialType socialType) {
        if (userRepository.existsBySocialIdAndSocialType(socialId, socialType)) {
            throw new ConflictException(String.format("이미 존재하는 유저 (%s - %s) 입니다", socialId, socialType), ErrorCode.CONFLICT_USER_EXCEPTION);
        }
    }
}