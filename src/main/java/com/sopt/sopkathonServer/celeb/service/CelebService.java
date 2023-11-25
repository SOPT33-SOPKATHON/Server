package com.sopt.sopkathonServer.celeb.service;

import com.sopt.sopkathonServer.celeb.domain.Celeb;
import com.sopt.sopkathonServer.celeb.dto.request.CelebCreateRequest;
import com.sopt.sopkathonServer.celeb.repository.CelebJpaRepository;
import com.sopt.sopkathonServer.room.domain.Room;
import com.sopt.sopkathonServer.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CelebService {


    private final RoomService roomService;
    private final CelebJpaRepository celebJpaRepository;

    public Long createCeleb(CelebCreateRequest celebrequest){
        Room room = roomService.getRoomById(celebrequest.roomId());

        Celeb celeb = celebJpaRepository.save(
                Celeb.builder()
                        .id(celebrequest.roomId())
                        .nickname(celebrequest.nickname())
                        .celebContent(celebrequest.celebText())
                        .room(room)
                        .postIt(celebrequest.postIt()).build());
        return celeb.getId();



    }



}
