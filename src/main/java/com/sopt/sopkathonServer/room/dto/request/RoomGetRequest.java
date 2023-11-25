package com.sopt.sopkathonServer.room.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RoomGetRequest(
        @JsonProperty("user_id")
        Long userId
) {
}
