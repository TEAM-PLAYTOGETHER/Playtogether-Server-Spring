package com.deploy.playtogether.service.lightUser;

import com.deploy.playtogether.common.exception.ErrorCode;
import com.deploy.playtogether.common.exception.model.ConflictException;
import com.deploy.playtogether.common.exception.model.NotFoundException;
import com.deploy.playtogether.domain.light.Light;
import com.deploy.playtogether.domain.light.LightRepository;
import com.deploy.playtogether.domain.lightUser.LightUser;
import com.deploy.playtogether.domain.lightUser.LightUserRepository;
import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserRepository;
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
    public void enterLight(Long lightId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        Light light = lightRepository.findById(lightId).orElseThrow(() -> new NotFoundException("존재하지 않는 번개입니다."));
        Optional<LightUser> lightUser = lightUserRepository.findByUserIdAndLightId(userId, lightId);
        if (lightUser.isEmpty()){
            lightUserRepository.save(LightUser.newInstance(user, light));
        } else {
            throw new ConflictException("해당 번개는 이미 참여한 상태입니다.", ErrorCode.CONFLICT_EXCEPTION);
        }

    }

    @Transactional
    public void outLight(Long lightId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        Light light = lightRepository.findById(lightId).orElseThrow(() -> new NotFoundException("존재하지 않는 번개입니다."));
        lightUserRepository.deleteByLightIdAndUserId(user.getId(), light.getId());
    }
}
