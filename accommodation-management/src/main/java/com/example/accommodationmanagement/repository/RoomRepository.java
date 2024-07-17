package com.example.accommodationmanagement.repository;

import com.example.accommodationmanagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
