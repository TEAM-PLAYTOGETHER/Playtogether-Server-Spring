package com.deploy.playtogether.controller.auth.dto.request;

import com.deploy.playtogether.domain.user.UserSocialType;
import com.deploy.playtogether.service.auth.dto.request.LoginDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequestDto {
    @NotBlank(message = "{auth.token.notBlank}")
    private String token;

    @NotNull(message = "{user.socialType.notNull}")
    private UserSocialType socialType;

    public LoginDto toServiceDto() {
        return LoginDto.of(token, socialType);
    }
}
