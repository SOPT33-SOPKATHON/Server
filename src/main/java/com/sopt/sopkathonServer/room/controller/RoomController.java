package com.sopt.sopkathonServer.room.controller;

import com.sopt.sopkathonServer.celeb.service.CelebService;
import com.sopt.sopkathonServer.room.dto.request.RoomCreateRequest;
import com.sopt.sopkathonServer.room.dto.request.RoomGetRequest;
import com.sopt.sopkathonServer.room.dto.response.RoomCreateResponse;
import com.sopt.sopkathonServer.room.dto.response.RoomListResponse;
import com.sopt.sopkathonServer.room.dto.response.RoomResponse;
import com.sopt.sopkathonServer.common.dto.ApiResponse;
import com.sopt.sopkathonServer.common.exception.enums.SuccessType;
import com.sopt.sopkathonServer.room.dto.response.RoomGetResponse;
import com.sopt.sopkathonServer.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sopt.sopkathonServer.common.exception.enums.SuccessType.ROOM_CREATE_SUCCESS;
import static com.sopt.sopkathonServer.common.exception.enums.SuccessType.ROOM_LIST_SUCCESS;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{room_uuid}")
    public ApiResponse<RoomGetResponse> getRoom (@PathVariable final String room_uuid){
        return ApiResponse.success(SuccessType.GET_ROOM_SUCCESS, roomService.getRoom(room_uuid));
    }

    @PostMapping("/create")
    ApiResponse<RoomCreateResponse> createRoom(@RequestBody RoomCreateRequest request) {
        return ApiResponse.success(ROOM_CREATE_SUCCESS, roomService.createRoom(request));
    }

    @PostMapping
    ApiResponse<RoomListResponse> getRoomList(@RequestBody RoomGetRequest request) {
        return ApiResponse.success(ROOM_LIST_SUCCESS, roomService.getRoomList(request));
    }

}
