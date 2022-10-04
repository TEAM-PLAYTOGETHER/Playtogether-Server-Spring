package com.deploy.playtogether.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.deploy.playtogether.common.exception.StatusCode.SUCCESS;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    SIGNUP_SUCCESS(SUCCESS, "회원가입이 완료되었습니다."),
    LOGIN_SUCCESS(SUCCESS, "로그인이 완료되었습니다."),
    LIGHT_ADD_SUCCESS(SUCCESS, "번개 생성이 완료되었습니다."),
    CREW_ADD_SUCCESS(SUCCESS, "동아리 생성이 완료되었습니다."),
    ENTER_LIGHT_SUCCESS(SUCCESS, "번개 참여가 완료되었습니다."),
    OUT_LIGHT_SUCCESS(SUCCESS, "번개 참여 취소가 완료되었습니다."),
    LIGHT_SCRAP_SUCCESS(SUCCESS, "번개 찜하기가 완료되었습니다."),
    LIGHT_DELETE_SCRAP_SUCCESS(SUCCESS, "번재 찜하기가 취소 되었습니다."),
    REPORT_LIGHT_SUCCESS(SUCCESS, "번개 신고가 완료되었습니다."),
    HOT_LIGHT_SUCCESS(SUCCESS, "인기 번개 조회 성공"),
    ;

    private final StatusCode statusCode;
    private final String message;

    public int getStatus(){
        return statusCode.getStatus();
    }
}
