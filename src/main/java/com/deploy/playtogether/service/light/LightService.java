package com.deploy.playtogether.service.light;

import com.deploy.playtogether.common.exception.ErrorCode;
import com.deploy.playtogether.common.exception.model.ConflictException;
import com.deploy.playtogether.common.exception.model.NotFoundException;
import com.deploy.playtogether.domain.crew.Crew;
import com.deploy.playtogether.domain.crew.CrewRepository;
import com.deploy.playtogether.domain.light.Light;
import com.deploy.playtogether.domain.lightImage.LightImage;
import com.deploy.playtogether.domain.lightImage.LightImageRepository;
import com.deploy.playtogether.domain.light.LightRepository;
import com.deploy.playtogether.domain.lightUser.LightUser;
import com.deploy.playtogether.domain.lightUser.LightUserRepository;
import com.deploy.playtogether.domain.reportLight.ReportLight;
import com.deploy.playtogether.domain.reportLight.ReportLightRepository;
import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.service.light.dto.request.LightDto;
import com.deploy.playtogether.service.light.dto.request.ReportLightDto;
import com.deploy.playtogether.service.light.dto.response.HotLightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LightService {
    private final LightRepository lightRepository;
    private final LightImageRepository lightImageRepository;
    private final LightUserRepository lightUserRepository;
    private final UserRepository userRepository;
    private final CrewRepository crewRepository;
    private final ReportLightRepository reportLightRepository;

    @Transactional
    public void createLight(final Long userId, final Long crewId, final LightDto request, final List<String> imagePath) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        final Crew crew = crewRepository.findById(crewId).orElseThrow(() -> new NotFoundException("존재하지 않는 동아리 입니다."));
        final Light light = lightRepository.save(Light.newInstance(
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
        saveLightImage(imagePath, light);
        lightUserRepository.save(LightUser.newInstance(light.getUser(), light));
    }

    @Transactional
    public void reportLight(final Long crewId, final Long lightId, final Long userId, final ReportLightDto request) {
        crewRepository.findById(crewId).orElseThrow(() -> new NotFoundException("존재하지 않는 동아리 입니다.", ErrorCode.NOT_FOUND_EXCEPTION));
        final User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다.", ErrorCode.NOT_FOUND_USER_EXCEPTION));
        final Light light = lightRepository.findById(lightId).orElseThrow(() -> new NotFoundException("존재하지 않는 번개입니다.", ErrorCode.NOT_FOUND_EXCEPTION));

        checkLightReport(user, light);

        reportLightRepository.save(
                ReportLight.newInstance(
                        user,
                        request.getReportReason(),
                        light
                )
        );
    }

    @Transactional
    public List<HotLightResponse> getHotLight(Long crewId) {
        crewRepository.findById(crewId).orElseThrow(() -> new NotFoundException("존재하지 않는 동아리 입니다."));
        Pageable page = PageRequest.of(0, 5);
        List<Light> lights = lightRepository.findAllByOrderByScpCntDesc(page);

        return lights.stream()
                .map(l -> HotLightResponse.of(
                        l.getId(), l.getCategory(), l.getTitle(), l.getDate(), l.getTime(),
                        l.getPlace(), l.getPeopleCnt(), l.getScpCnt(),
                        l.getLightMemberCnt()
                )).collect(Collectors.toList());
    }

    private List<String> saveLightImage(final List<String> imagePath, final Light light) {
        final List<String> imgList = new ArrayList<>();
        imagePath.stream()
                .map(imgUrl -> LightImage.newInstance(imgUrl, light))
                .forEach(img -> {
                    lightImageRepository.save(img);
                    imgList.add(img.getImgUrl());
                });
        return imgList;
    }

    private void checkLightReport(final User user, final Light light) {
        final Optional<ReportLight> reportLight = reportLightRepository.findByUserIdAndLightId(user.getId(), light.getId());
        if (reportLight.isPresent()) {
            throw new ConflictException("해당 번개를 이미 신고한 상태입니다.", ErrorCode.CONFLICT_EXCEPTION);
        }
    }
}
