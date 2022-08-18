package com.deploy.playtogether.service.light;

import com.deploy.playtogether.common.exception.model.NotFoundException;
import com.deploy.playtogether.domain.crew.Crew;
import com.deploy.playtogether.domain.crew.repository.CrewRepository;
import com.deploy.playtogether.domain.light.Light;
import com.deploy.playtogether.domain.light.LightImage;
import com.deploy.playtogether.domain.light.repository.LightImageRepository;
import com.deploy.playtogether.domain.light.repository.LightRepository;
import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.service.light.dto.request.LightDto;
import com.deploy.playtogether.service.light.dto.response.LightResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LightService {
    private final LightRepository lightRepository;
    private final LightImageRepository lightImageRepository;
    private final UserRepository userRepository;
    private final CrewRepository crewRepository;

    @Transactional
    public LightResponseDto createLight(Long userId, Long crewId, LightDto request, List<String> imagePath) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        Crew crew = crewRepository.findById(crewId).orElseThrow(() -> new NotFoundException("존재하지 않는 동아리 입니다."));
        Light light = lightRepository.save(Light.newInstance(
                request.getTitle(),
                request.getPlace(),
                request.getPeopleCount(),
                request.getDescription(),
                request.getDate(),
                request.getTime(),
                request.getCategory(),
                user,
                crew
        ));
        List<String> lightImageList = saveLightImage(imagePath, light);

        return LightResponseDto.of(
                light.getId(),
                light.getCategory(),
                light.getTitle(),
                light.getDate(),
                light.getTime(),
                lightImageList,
                light.getPlace(),
                light.getPeopleCnt(),
                light.getDescription(),
                light.getUser().getId(),
                light.getCrew().getId()
        );
    }

    private List<String> saveLightImage(List<String> imagePath, Light light) {
        List<String> imgList = new ArrayList<>();
        for (String imgUrl : imagePath) {
            LightImage img = LightImage.newInstance(imgUrl, light);
            lightImageRepository.save(img);
            imgList.add(img.getImgUrl());
        }
        return imgList;
    }
}
