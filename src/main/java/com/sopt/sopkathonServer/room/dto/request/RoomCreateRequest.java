package com.sopt.sopkathonServer.room.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RoomCreateRequest(
        @JsonProperty("user_id")
        Long userId,

        @JsonProperty("room_name")
        String roomName,

        @JsonProperty("room_content")
        String roomContent,

        @JsonProperty("celeb_year")
        int celebYear,

        @JsonProperty("celeb_month")
        int celebMonth,

        @JsonProperty("celeb_day")
        int celebDay
) {
}
