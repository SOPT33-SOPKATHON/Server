package com.sopt.sopkathonServer.room.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sopt.sopkathonServer.room.domain.Room;

import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RoomResponse(
        Long roomId,
        String roomName,
        String time,
        String roomUUID
) {
    public static RoomResponse of(Room room) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formattedDate = room.getTime().format(formatter);

        // 요일을 한국어로 변환
        String dayOfWeekInKorean = room.getTime().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        // 최종 문자열 출력
        String finalString = formattedDate + " (" + dayOfWeekInKorean + ")";

        return new RoomResponse(
                room.getId(),
                room.getRoomName(),
                finalString,
                room.getRoomUUID()
        );
    }
}
