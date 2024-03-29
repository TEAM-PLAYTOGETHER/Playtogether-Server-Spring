package com.deploy.playtogether.config.interceptor;


import static com.deploy.playtogether.config.jwt.JwtHeader.AUTH;

import com.deploy.playtogether.config.jwt.JwtService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoginCheckHandler {
    private final JwtService jwtService;
    private static final long TEMP_GUEST_MODE = -1L;

    Long getUserId(final HttpServletRequest request) {
        final String token = request.getHeader(AUTH);
        if (!jwtService.verifyToken(token)) {
            return TEMP_GUEST_MODE;
        }

        final String subject = jwtService.getSubject(token);
        return convertToUserId(subject);
    }

    private long convertToUserId(final String subject) {
        try {
            return Long.parseLong(subject);
        } catch (final NumberFormatException e) {
            return TEMP_GUEST_MODE;
        }
    }

}
