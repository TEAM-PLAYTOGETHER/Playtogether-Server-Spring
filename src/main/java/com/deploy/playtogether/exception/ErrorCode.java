package com.deploy.playtogether.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    // 400 Bad Request
    VALIDATION_AUTH_TOKEN_EXCEPTION(StatusCode.BAD_REQUEST, "만료되거나 유효하지 않은 인증 토큰입니다"),
    VALIDATION_EXCEPTION(StatusCode.BAD_REQUEST, "잘못된 요청입니다"),
    VALIDATION_ENUM_VALUE_EXCEPTION(StatusCode.BAD_REQUEST, "잘못된 Enum 값 입니다"),
    VALIDATION_REQUEST_MISSING_EXCEPTION(StatusCode.BAD_REQUEST, "필수적인 요청 값이 입력되지 않았습니다"),
    VALIDATION_WRONG_TYPE_EXCEPTION(StatusCode.BAD_REQUEST, "잘못된 타입이 입력되었습니다."),
    VALIDATION_SOCIAL_TYPE_EXCEPTION(StatusCode.BAD_REQUEST, "잘못된 소셜 프로바이더 입니다."),

    // 401 UnAuthorized
    UNAUTHORIZED_EXCEPTION(StatusCode.UNAUTHORIZED, "세션이 만료되었습니다. 다시 로그인 해주세요"),

    // 403 Forbidden
    FORBIDDEN_EXCEPTION(StatusCode.FORBIDDEN, "허용하지 않는 요청입니다."),

    // 404 Not Found
    NOT_FOUND_EXCEPTION(StatusCode.NOT_FOUND, "존재하지 않습니다"),
    NOT_FOUND_USER_EXCEPTION(StatusCode.NOT_FOUND, "탈퇴하거나 존재하지 않는 유저입니다"),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED_EXCEPTION(StatusCode.METHOD_NOT_ALLOWED, "지원하지 않는 메소드 입니다"),

    // 406 Not Acceptable
    NOT_ACCEPTABLE_EXCEPTION(StatusCode.NOT_ACCEPTABLE, "Not Acceptable"),

    // 409 Conflict
    CONFLICT_EXCEPTION(StatusCode.CONFLICT, "이미 존재합니다"),
    CONFLICT_NICKNAME_EXCEPTION(StatusCode.CONFLICT, "이미 사용중인 닉네임입니다.\n다른 닉네임을 이용해주세요"),
    CONFLICT_USER_EXCEPTION(StatusCode.CONFLICT, "이미 해당 계정으로 회원가입하셨습니다.\n로그인 해주세요"),

    // 415 Unsupported Media Type
    UNSUPPORTED_MEDIA_TYPE_EXCEPTION(StatusCode.UNSUPPORTED_MEDIA_TYPE, "해당하는 미디어 타입을 지원하지 않습니다."),

    // 500 Internal Server Exception
    INTERNAL_SERVER_EXCEPTION(StatusCode.INTERNAL_SERVER, "예상치 못한 서버 에러가 발생하였습니다."),

    // 502 Bad Gateway
    BAD_GATEWAY_EXCEPTION(StatusCode.BAD_GATEWAY, "일시적인 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),

    // 503 Service UnAvailable
    SERVICE_UNAVAILABLE_EXCEPTION(StatusCode.SERVICE_UNAVAILABLE, "현재 점검 중입니다.\n잠시 후 다시 시도해주세요!"),
    ;

    private final StatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}
