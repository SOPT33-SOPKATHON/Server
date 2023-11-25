package com.sopt.sopkathonServer.celeb.domain;

import com.sopt.sopkathonServer.common.domain.BaseTimeEntity;
import com.sopt.sopkathonServer.room.domain.Room;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "celeb")
public class Celeb extends BaseTimeEntity {

    @Id
    @Column(name = "celeb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String celebContent;

    @Column(nullable = false)
    private int postIt;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Builder
    public Celeb(Long id, String nickname, String celebContent, int postIt, Room room){
        this.id = id;
        this.nickname = nickname;
        this.celebContent = celebContent;
        this.postIt = postIt;
        this.room = room;
    }

}
