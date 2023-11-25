package com.sopt.sopkathonServer.common.exception.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessType {

    /**
     * 200 OK
     */
    PROCESS_SUCCESS(HttpStatus.OK, "OK"),

    /**
     * 201 CREATED
     */

    CELEB_CREATE_SUCCESS(HttpStatus.OK, "축하 메시지가 성공적으로 생성됐습니다"),

    /**
     * 204 NO CONTENT
     */
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
