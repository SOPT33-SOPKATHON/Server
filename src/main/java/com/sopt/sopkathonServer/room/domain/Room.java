package com.sopt.sopkathonServer.room.domain;

import com.sopt.sopkathonServer.celeb.domain.Celeb;
import com.sopt.sopkathonServer.common.domain.BaseTimeEntity;
import com.sopt.sopkathonServer.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "room")
public class Room extends BaseTimeEntity {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomName;

    private String roomContent;

    private LocalDateTime time;

    @Column(nullable = false)
    private String roomUUID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "room")
    private List<Celeb> celebList = new ArrayList<>();

    public void addCelebList(Celeb celeb) {
        celebList.add(celeb);
    }

    public int calCelebNum(List<Celeb> celebList){
        return celebList.size();
    }

    public Room(String roomName, String roomContent, LocalDateTime time, String roomUUID, User user) {
        this.roomName = roomName;
        this.roomContent = roomContent;
        this.time = time;
        this.roomUUID = roomUUID;
        this.user = user;
    }

    public static Room of(String roomName, String roomContent, LocalDateTime time, String roomUUID, User user) {
        return new Room(roomName, roomContent, time, roomUUID, user);
    }
}
