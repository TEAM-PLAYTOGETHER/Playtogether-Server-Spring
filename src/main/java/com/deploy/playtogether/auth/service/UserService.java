package com.deploy.playtogether.auth.service;

import com.deploy.playtogether.auth.domain.User;
import com.deploy.playtogether.auth.infrastructure.UserRepository;
import com.deploy.playtogether.auth.service.request.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long registerUser(CreateUserDto request) {
        UserServiceUtils.validateNotExistsUser(userRepository, request.getSocialId(), request.getSocialType());
        UserServiceUtils.validateNotExistsUserName(userRepository, request.getNickname());
        User user = User.newInstance(request.getSocialId(), request.getSocialType(), request.getNickname(), request.getEmail());
        userRepository.save(user);
        return user.getId();
    }
}
