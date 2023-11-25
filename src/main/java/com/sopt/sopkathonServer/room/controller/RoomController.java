package com.sopt.sopkathonServer.room.controller;


import com.sopt.sopkathonServer.common.dto.ApiResponse;
import com.sopt.sopkathonServer.common.exception.enums.SuccessType;
import com.sopt.sopkathonServer.room.dto.response.RoomGetResponse;
import com.sopt.sopkathonServer.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{room_uuid}")
    public ApiResponse<RoomGetResponse> getRoom (@PathVariable final String room_uuid){
        return ApiResponse.success(SuccessType.GET_ROOM_SUCCESS, roomService.getRoom(room_uuid));
    }

}
