package com.sopt.sopkathonServer.celeb.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sopt.sopkathonServer.celeb.domain.Celeb;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CelebGetResponse(
        Long celeb_id,
        String nickname,
        String celebContent,
        int postIt
) {
    public static CelebGetResponse of(Celeb celeb){
        return new CelebGetResponse(
                celeb.getId(),
                celeb.getNickname(),
                celeb.getCelebContent(),
                celeb.getPostIt()
        );
    }

}
