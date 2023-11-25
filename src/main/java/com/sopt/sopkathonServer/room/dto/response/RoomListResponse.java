package com.sopt.sopkathonServer.room.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sopt.sopkathonServer.room.domain.Room;
import com.sopt.sopkathonServer.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RoomListResponse(
    String userNickname,
    List<RoomResponse> roomList
) {
    public static RoomListResponse of(User user, List<Room> originalRoomList) {
        List<RoomResponse> roomList = originalRoomList.stream()
                .map(RoomResponse::of) // 각 Room 객체를 RoomResponse 객체로 변환
                .collect(Collectors.toList());

        return new RoomListResponse(user.getNickname(), roomList);
    }
}
