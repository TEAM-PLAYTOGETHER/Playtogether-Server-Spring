package com.deploy.playtogether.service.user;

import com.deploy.playtogether.domain.user.User;
import com.deploy.playtogether.domain.user.UserRepository;
import com.deploy.playtogether.domain.user.UserSocialType;
import com.deploy.playtogether.external.client.kakao.KaKaoAuthApiClient;
import com.deploy.playtogether.service.auth.AuthService;
import com.deploy.playtogether.service.auth.dto.request.LoginDto;
import com.deploy.playtogether.service.auth.impl.KaKaoAuthService;
import com.deploy.playtogether.service.user.dto.request.CreateUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@Transactional
@SpringBootTest
class UserServiceTest {
    private UserService userService;
    private UserRepository stubUserRepository;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        stubUserRepository = mock(UserRepository.class);
        userService = new UserService(stubUserRepository);
        authService = mock(AuthService.class);
    }
    @Test
    @DisplayName("유저_회원가입_테스트")
    void singUp(){
        given(stubUserRepository.findUserById(any()))
                .willReturn(User.newInstance("tempId", UserSocialType.APPLE, "tempNickName", "tempEmail"));

        User user = User.newInstance("tempId", UserSocialType.APPLE, "tempNickName", "tempEmail");
        Long userId = userService.registerUser(CreateUserDto.of(user.getSocialId(), user.getSocialType(), user.getNickname(), user.getEmail()));

        User givenUser = stubUserRepository.findUserById(userId);

        assertThat(user.getNickname()).isEqualTo(givenUser.getNickname());
        assertThat(user.getId()).isEqualTo(givenUser.getId());
        assertThat(user.getEmail()).isEqualTo(givenUser.getEmail());
        assertThat(user.getSocialId()).isEqualTo(givenUser.getSocialId());
        assertThat(user.getSocialType()).isEqualTo(givenUser.getSocialType());
    }
    @Test
    @DisplayName("유저_로그인_테스트")
    void Login(){
        given(stubUserRepository.findUserById(any()))
                .willReturn(User.newInstance("tempId", UserSocialType.APPLE, "tempNickName", "tempEmail"));

        User user = User.newInstance("tempId", UserSocialType.APPLE, "tempNickName", "tempEmail");
        Long userId = authService.login(LoginDto.of(user.getSocialId(), user.getSocialType()));

        User givenUser = stubUserRepository.findUserById(userId);

        assertThat(user.getNickname()).isEqualTo(givenUser.getNickname());
        assertThat(user.getId()).isEqualTo(givenUser.getId());
        assertThat(user.getEmail()).isEqualTo(givenUser.getEmail());
        assertThat(user.getSocialId()).isEqualTo(givenUser.getSocialId());
        assertThat(user.getSocialType()).isEqualTo(givenUser.getSocialType());

    }
}