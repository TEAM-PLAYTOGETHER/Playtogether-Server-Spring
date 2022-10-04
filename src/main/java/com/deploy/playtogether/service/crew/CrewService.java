package com.deploy.playtogether.service.crew;

import com.deploy.playtogether.common.exception.model.NotFoundException;
import com.deploy.playtogether.domain.crew.Crew;
import com.deploy.playtogether.domain.crew.CrewRepository;
import com.deploy.playtogether.domain.crewUser.CrewUser;
import com.deploy.playtogether.domain.crewUser.CrewUserRepository;
import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.service.crew.dto.request.CrewDto;
import com.deploy.playtogether.service.crew.dto.response.CrewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;


@RequiredArgsConstructor
@Service
public class CrewService {
    private final CrewRepository crewRepository;
    private final CrewUserRepository crewUserRepository;
    private final UserRepository userRepository;

    public void createCrew(final Long userId, final CrewDto request) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저 입니다."));
        final String code = CreateRandomCode();
        final Crew crew = crewRepository.save(Crew.newInstance(
                user,
                request.getCrewName(),
                request.getDescription(),
                code
        ));
        crewUserRepository.save(CrewUser.newInstance(crew.getUser(), crew));
    }
    private String CreateRandomCode() {
        final int leftLimit = 65; // 'A'
        final int rightLimit = 90; // 'Z'
        final int targetStringLength = 6;
        final Random random = new Random();
        final String randomCode = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return randomCode;
    }
}
