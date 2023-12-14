package com.sopt.sopkathonServer.room.service;

import com.sopt.sopkathonServer.common.exception.enums.ErrorType;
import com.sopt.sopkathonServer.common.exception.model.BusinessException;
import com.sopt.sopkathonServer.common.exception.slack.SlackUtil;
import com.sopt.sopkathonServer.common.util.BadWordFilterService;
import com.sopt.sopkathonServer.room.domain.Room;
import com.sopt.sopkathonServer.room.dto.request.RoomCreateRequest;
import com.sopt.sopkathonServer.room.dto.request.RoomGetRequest;
import com.sopt.sopkathonServer.room.dto.response.RoomCreateResponse;
import com.sopt.sopkathonServer.room.dto.response.RoomListResponse;
import com.sopt.sopkathonServer.room.dto.response.RoomGetResponse;
import com.sopt.sopkathonServer.room.repository.RoomJpaRepository;
import com.sopt.sopkathonServer.user.domain.User;
import com.sopt.sopkathonServer.user.repository.UserJpaRepository;
import com.vane.badwordfiltering.BadWordFiltering;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomJpaRepository roomJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final BadWordFilterService badWordFilterService;

    private final SlackUtil slackUtil;

    public Room getRoomById(final Long roomId) {
        return roomJpaRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(ErrorType.ROOM_NOT_FOUND_EXCEPTION));
    }

    public RoomGetResponse getRoom(String roomUUID){
        Room existRoom = roomJpaRepository.findRoomByRoomUUID(roomUUID)
                .orElseThrow(() -> new BusinessException(ErrorType.ROOM_NOT_FOUND_EXCEPTION));

        return RoomGetResponse.of(existRoom);
    }

    @Transactional
    public RoomCreateResponse createRoom(RoomCreateRequest request) throws IOException {

        User user = userJpaRepository.findById(request.userId())
                .orElseThrow(() -> new BusinessException(ErrorType.USER_NOT_FOUND_EXCEPTION));

        String roomName = badWordFilterService.filterString(request.roomName());
        String roomContent = badWordFilterService.filterString(request.roomContent());

        String roomUUID = UUID.randomUUID().toString();

        // int 값으로 받은 연도, 월, 일을 LocalDate 객체로 변환
        LocalDate date = LocalDate.of(request.celebYear(), request.celebMonth(), request.celebDay());
        // LocalDate를 LocalDateTime으로 변환 (자정 시간을 사용)
        LocalDateTime dateTime = date.atStartOfDay();

        Room room = Room.of(roomName, roomContent, dateTime, roomUUID, user);

        user.getRoomList().add(room);

        roomJpaRepository.save(room);

        slackUtil.sendRoomAlert(room);

        return RoomCreateResponse.of(room);

    }

    public RoomListResponse getRoomList(RoomGetRequest request) {
        User user = userJpaRepository.findById(request.userId())
                .orElseThrow(() -> new BusinessException(ErrorType.USER_NOT_FOUND_EXCEPTION));

        return RoomListResponse.of(user, user.getRoomList());
    }

}
