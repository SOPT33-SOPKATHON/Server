package com.sopt.sopkathonServer.celeb.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CelebCreateRequest(
        @JsonProperty("room_id")
        Long roomId,
        String nickname,

        @JsonProperty("celeb_text")
        String celebText,

        @JsonProperty("post_it")
        int postIt

) {

}
