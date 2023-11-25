package com.sopt.sopkathonServer.room.service;


import com.sopt.sopkathonServer.celeb.domain.Celeb;
import com.sopt.sopkathonServer.common.dto.ApiResponse;
import com.sopt.sopkathonServer.common.exception.enums.ErrorType;
import com.sopt.sopkathonServer.common.exception.model.BusinessException;
import com.sopt.sopkathonServer.room.domain.Room;
import com.sopt.sopkathonServer.room.dto.response.RoomGetResponse;
import com.sopt.sopkathonServer.room.repository.RoomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomJpaRepository roomJpaRepository;

    public Room getRoomById(final Long roomId) {
        return roomJpaRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(ErrorType.ROOM_NOT_FOUND_EXCEPTION));
    }

    public RoomGetResponse getRoom(String roomUUID){
        Room existRoom = roomJpaRepository.findRoomByRoomUUID(roomUUID)
                .orElseThrow(() -> new BusinessException(ErrorType.ROOM_NOT_FOUND_EXCEPTION));

        return RoomGetResponse.of(existRoom);
    }




}
