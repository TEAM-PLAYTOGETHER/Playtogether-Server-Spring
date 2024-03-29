package com.deploy.playtogether.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    SIGNUP_SUCCESS(StatusCode.SUCCESS, "회원가입이 완료되었습니다."),
    LOGIN_SUCCESS(StatusCode.SUCCESS, "로그인이 완료되었습니다."),
    LIGHT_ADD_SUCCESS(StatusCode.SUCCESS, "번개 생성이 완료되었습니다."),
    CREW_ADD_SUCCESS(StatusCode.SUCCESS, "동아리 생성이 완료되었습니다."),
    ENTER_LIGHT_SUCCESS(StatusCode.SUCCESS, "번개 참여가 완료되었습니다."),
    OUT_LIGHT_SUCCESS(StatusCode.SUCCESS, "번개 참여 취소가 완료되었습니다."),
    LIGHT_SCRAP_SUCCESS(StatusCode.SUCCESS, "번개 찜하기가 완료되었습니다."),
    LIGHT_DELETE_SCRAP_SUCCESS(StatusCode.SUCCESS, "번재 찜하기가 취소 되었습니다."),
    REPORT_LIGHT_SUCCESS(StatusCode.SUCCESS, "번개 신고가 완료되었습니다."),
    HOT_LIGHT_SUCCESS(StatusCode.SUCCESS, "인기 번개 조회 성공"),
    NEW_LIGHT_SUCCESS(StatusCode.SUCCESS, "최신 번개 조회 성공"),
    OPEN_LIGHT_SUCCESS(StatusCode.SUCCESS, "내가 만든 번개 조회 성공"),
    ;

    private final StatusCode statusCode;
    private final String message;

    public int getStatus(){
        return statusCode.getStatus();
    }
}
