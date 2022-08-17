package com.deploy.playtogether.service.crew;

import com.deploy.playtogether.common.exception.model.NotFoundException;
import com.deploy.playtogether.controller.crew.dto.request.CrewRequestDto;
import com.deploy.playtogether.domain.crew.Crew;
import com.deploy.playtogether.domain.crew.repository.CrewRepository;
import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.service.crew.dto.request.CrewDto;
import com.deploy.playtogether.service.crew.dto.response.CrewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;


@RequiredArgsConstructor
@Service
public class CrewService {
    private final CrewRepository crewRepository;
    private final UserRepository userRepository;

    public CrewResponse createCrew(Long userId, CrewDto request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저 입니다."));
        String code = CreateRandomCode();
        Crew crew = crewRepository.save(Crew.newInstance(
                user,
                request.getCrewName(),
                request.getDescription(),
                code
        ));
        return CrewResponse.of(
                crew.getId(),
                crew.getName(),
                crew.getDescription(),
                crew.getCode(),
                crew.getUser().getId()
        );
    }
    private String CreateRandomCode() {
        int leftLimit = 65; // 'A'
        int rightLimit = 90; // 'Z'
        int targetStringLength = 6;
        Random random = new Random();
        String randomCode = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return randomCode;
    }
}
