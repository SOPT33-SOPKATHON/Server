package com.sopt.sopkathonServer.user.domain.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SocialLoginResponse(
        Long userId
) {
    public static SocialLoginResponse of(Long userId) {
        return new SocialLoginResponse(
                userId = userId
        );
    }
}
