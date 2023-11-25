package com.sopt.sopkathonServer.room.repository;

import com.sopt.sopkathonServer.room.domain.Room;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomJpaRepository extends JpaRepository<Room, Long> {
    Optional<Room> findRoomByRoomUUID(String UUID);


}
