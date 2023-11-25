package com.sopt.sopkathonServer.room.dto.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sopt.sopkathonServer.celeb.domain.Celeb;
import com.sopt.sopkathonServer.room.domain.Room;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RoomGetResponse(
        Long roomId,
        String roomName,
        String roomContent,
        int year,
        int month,
        int day,
        int celebNum,
        List<Celeb> celebList


) {
    public static RoomGetResponse of(Room room){
        return new RoomGetResponse(
                room.getId(),
                room.getRoomName(),
                room.getRoomContent(),
                room.getYear(),
                room.getMonth(),
                room.calCelebNum(room.getCelebList()),
                room.getDay(),
                room.getCelebList()

        );

    }
}
