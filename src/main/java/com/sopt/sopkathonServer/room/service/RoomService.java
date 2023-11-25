package com.sopt.sopkathonServer.room.service;


import com.sopt.sopkathonServer.common.exception.enums.ErrorType;
import com.sopt.sopkathonServer.common.exception.model.BusinessException;
import com.sopt.sopkathonServer.room.domain.Room;
import com.sopt.sopkathonServer.room.repository.RoomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {
    private final RoomJpaRepository roomJpaRepository;

    public Room getRoomById(final Long roomId) {
        return roomJpaRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(ErrorType.ROOM_NOT_FOUND_EXCEPTION));
    }

}
