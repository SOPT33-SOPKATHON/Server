package com.sopt.sopkathonServer.celeb.domain;

import com.sopt.sopkathonServer.common.domain.BaseTimeEntity;
import com.sopt.sopkathonServer.room.domain.Room;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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
    private PostIt postIt;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
