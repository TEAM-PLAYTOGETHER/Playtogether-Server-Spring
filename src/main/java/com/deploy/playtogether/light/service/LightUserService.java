package com.deploy.playtogether.light.service;

import com.deploy.playtogether.exception.ErrorCode;
import com.deploy.playtogether.exception.model.ConflictException;
import com.deploy.playtogether.exception.model.NotFoundException;
import com.deploy.playtogether.light.domain.Light;
import com.deploy.playtogether.light.infrastructure.LightRepository;
import com.deploy.playtogether.light.domain.LightUser;
import com.deploy.playtogether.light.infrastructure.LightUserRepository;
import com.deploy.playtogether.auth.domain.User;
import com.deploy.playtogether.auth.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LightUserService {
    private final LightUserRepository lightUserRepository;
    private final LightRepository lightRepository;
    private final UserRepository userRepository;

    @Transactional
    public void enterLight(final Long lightId, final Long userId) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        final Light light = lightRepository.findById(lightId).orElseThrow(() -> new NotFoundException("존재하지 않는 번개입니다."));
        checkLightUser(lightId, userId);
        lightUserRepository.save(LightUser.newInstance(user, light));
        light.updateMemberCnt();
    }

    private void checkLightUser(Long lightId, Long userId) {
        final Optional<LightUser> lightUser = lightUserRepository.findByUserIdAndLightId(userId, lightId);
        if (lightUser.isPresent()){
            throw new ConflictException("해당 번개는 이미 참여한 상태입니다.", ErrorCode.CONFLICT_EXCEPTION);
        }
    }

    @Transactional
    public void outLight(final Long lightId, final Long userId) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        final Light light = lightRepository.findById(lightId).orElseThrow(() -> new NotFoundException("존재하지 않는 번개입니다."));
        lightUserRepository.deleteByLightIdAndUserId(user.getId(), light.getId());
    }
}
