package com.sopt.sopkathonServer.room.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sopt.sopkathonServer.room.domain.Room;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RoomCreateResponse(
        Long roomId,
        String roomUUID
) {
    public static RoomCreateResponse of(Room room) {
        return new RoomCreateResponse(
                room.getId(),
                room.getRoomUUID()
        );
    }
}
