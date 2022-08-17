package com.deploy.playtogether.service.light;

import com.deploy.playtogether.common.exception.model.NotFoundException;
import com.deploy.playtogether.domain.crew.Crew;
import com.deploy.playtogether.domain.crew.repository.CrewRepository;
import com.deploy.playtogether.domain.light.Light;
import com.deploy.playtogether.domain.light.repository.LightRepository;
import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.service.light.dto.request.LightDto;
import com.deploy.playtogether.service.light.dto.response.LightResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LightService {
    private final LightRepository lightRepository;
    private final UserRepository userRepository;
    private final CrewRepository crewRepository;

    @Transactional
    public LightResponseDto createLight(Long userId, Long crewId, LightDto request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        Crew crew = crewRepository.findById(crewId).orElseThrow(() -> new NotFoundException("존재하지 않는 동아리 입니다."));
        Light light = lightRepository.save(Light.newInstance(
                request.getTitle(),
                request.getPlace(),
                request.getPeopleCount(),
                request.getLightImages(),
                request.getDescription(),
                request.getDate(),
                request.getTime(),
                request.getCategory(),
                user,
                crew
        ));
        return LightResponseDto.of(
                light.getId(),
                light.getCategory(),
                light.getTitle(),
                light.getDate(),
                light.getTime(),
                light.getImageUrls(),
                light.getPlace(),
                light.getPeopleCnt(),
                light.getDescription(),
                light.getUser().getId(),
                light.getCrew().getId()
        );
    }
}
