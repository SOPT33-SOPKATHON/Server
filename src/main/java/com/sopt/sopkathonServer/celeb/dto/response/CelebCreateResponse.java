package com.sopt.sopkathonServer.celeb.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sopt.sopkathonServer.celeb.domain.Celeb;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CelebCreateResponse(
        Long celebId
) {
    public static CelebCreateResponse of(Celeb celeb){
        return new CelebCreateResponse(
                celeb.getId()
        );
    }


}
