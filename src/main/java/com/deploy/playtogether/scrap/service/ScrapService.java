package com.deploy.playtogether.scrap.service;

import com.deploy.playtogether.exception.ErrorCode;
import com.deploy.playtogether.exception.model.ConflictException;
import com.deploy.playtogether.exception.model.NotFoundException;
import com.deploy.playtogether.light.domain.Light;
import com.deploy.playtogether.light.infrastructure.LightRepository;
import com.deploy.playtogether.scrap.domain.Scrap;
import com.deploy.playtogether.scrap.infrastructure.ScrapRepository;
import com.deploy.playtogether.auth.domain.User;
import com.deploy.playtogether.auth.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final LightRepository lightRepository;

    @Transactional
    public void addScrap(final Long lightId, final Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다.", ErrorCode.NOT_FOUND_USER_EXCEPTION));
        final Light light = lightRepository.findById(lightId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 번개 입니다.", ErrorCode.NOT_FOUND_EXCEPTION));
        checkLightScrap(user, light);
        scrapRepository.save(Scrap.of(lightId, userId));
        light.updateScpCnt();
    }

    private void checkLightScrap(User user, Light light) {
        final Optional<Scrap> scrap = scrapRepository.findByLightIdAndUserId(light.getId(), user.getId());
        if (scrap.isPresent()){
            throw new ConflictException("해당 번개는 이미 찜한 상태입니다.", ErrorCode.CONFLICT_EXCEPTION);
        }
    }


    @Transactional
    public void deleteScrap(final Long lightId, final Long userId) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        final Light light = lightRepository.findById(lightId).orElseThrow(() -> new NotFoundException("존재하지 않는 번개입니다."));
        scrapRepository.deleteByLightIdAndUserId(light.getId(), user.getId());
    }
}
