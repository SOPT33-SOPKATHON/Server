package com.sopt.sopkathonServer.room.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sopt.sopkathonServer.room.domain.Room;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RoomResponse(
        Long roomId,
        String roomName,
        int year,
        int month,
        int day,
        String roomUUID
) {
    public static RoomResponse of(Room room) {
        return new RoomResponse(
                room.getId(),
                room.getRoomName(),
                room.getYear(),
                room.getMonth(),
                room.getDay(),
                room.getRoomUUID()
        );
    }
}
