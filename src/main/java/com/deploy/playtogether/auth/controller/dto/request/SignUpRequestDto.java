package com.deploy.playtogether.auth.controller.dto.request;

import com.deploy.playtogether.auth.domain.UserSocialType;
import com.deploy.playtogether.auth.service.request.SignUpDto;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequestDto {
    @NotBlank(message = "{auth.token.notBlank}")
    private String token;

    @NotBlank(message = "{user.nickname.notBlank}")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,15}$", message = "{user.nickname.format}")
    private String nickname;

    @NotNull(message = "{user.email.notNull")
    @Email(message = "{user.email.format}")
    private String email;

    @NotNull(message = "{user.socialType.notNull}")
    private UserSocialType socialType;

    public SignUpDto toServiceDto() {
        return SignUpDto.of(token, nickname, email, socialType);
    }
}