package com.sopt.sopkathonServer.room.repository;

import com.sopt.sopkathonServer.room.domain.Room;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<Room, Long> {


}
