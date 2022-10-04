package com.deploy.playtogether.service.scrap;

import com.deploy.playtogether.common.exception.ErrorCode;
import com.deploy.playtogether.common.exception.model.ConflictException;
import com.deploy.playtogether.common.exception.model.NotFoundException;
import com.deploy.playtogether.domain.light.Light;
import com.deploy.playtogether.domain.light.LightRepository;
import com.deploy.playtogether.domain.scrap.Scrap;
import com.deploy.playtogether.domain.scrap.ScrapRepository;
import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserRepository;
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
