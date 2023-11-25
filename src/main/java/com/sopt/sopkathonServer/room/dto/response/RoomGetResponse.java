package com.sopt.sopkathonServer.room.dto.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sopt.sopkathonServer.celeb.domain.Celeb;
import com.sopt.sopkathonServer.celeb.dto.response.CelebGetResponse;
import com.sopt.sopkathonServer.room.domain.Room;

import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RoomGetResponse(
        Long roomId,
        String roomName,
        String roomContent,
        String time,
        int celebNum,
        List<CelebGetResponse> celebList
) {
    public static RoomGetResponse of(Room room){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd");
        String formattedDate = room.getTime().format(formatter);

        // 요일을 한국어로 변환
        String dayOfWeekInKorean = room.getTime().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        // 최종 문자열 출력
        String finalString = formattedDate + " (" + dayOfWeekInKorean + ")";

        return new RoomGetResponse(
                room.getId(),
                room.getRoomName(),
                room.getRoomContent(),
                finalString,
                room.calCelebNum(room.getCelebList()),
                room.getCelebList()
                        .stream()
                        .map(CelebGetResponse::of)
                        .collect(Collectors.toList())
        );

    }
}
