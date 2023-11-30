package com.sopt.sopkathonServer.celeb.service;

import com.sopt.sopkathonServer.celeb.domain.Celeb;
import com.sopt.sopkathonServer.celeb.dto.request.CelebCreateRequest;
import com.sopt.sopkathonServer.celeb.dto.response.CelebCreateResponse;
import com.sopt.sopkathonServer.celeb.repository.CelebJpaRepository;
import com.sopt.sopkathonServer.common.exception.enums.ErrorType;
import com.sopt.sopkathonServer.common.exception.model.BusinessException;
import com.sopt.sopkathonServer.room.domain.Room;
import com.sopt.sopkathonServer.room.repository.RoomJpaRepository;
import com.vane.badwordfiltering.BadWordFiltering;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CelebService {


    private final RoomJpaRepository roomJpaRepository;
    private final CelebJpaRepository celebJpaRepository;
    private final BadWordFiltering badWordFiltering = new BadWordFiltering();

    @Transactional
    public CelebCreateResponse createCeleb(CelebCreateRequest celebrequest){
        Room room = roomJpaRepository.findRoomByRoomUUID(celebrequest.roomUuid())
                .orElseThrow(() -> new BusinessException(ErrorType.ROOM_NOT_FOUND_EXCEPTION));

        String[] symbols = new String[]{"!", "@", "#", "$", "%", "^", "&", "*", "_"};
        String celebNickname = badWordFiltering.change(celebrequest.nickname(), symbols);
        String celebText = badWordFiltering.change(celebrequest.celebText(), symbols);

        Celeb celeb = celebJpaRepository.save(
                Celeb.builder()
                        .nickname(celebNickname)
                        .celebContent(celebText)
                        .room(room)
                        .postIt(celebrequest.postIt()).build());

        room.getCelebList().add(celeb);
        return CelebCreateResponse.of(celeb);
    }
}
