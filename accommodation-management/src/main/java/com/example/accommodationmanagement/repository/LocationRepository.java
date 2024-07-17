package com.example.accommodationmanagement.repository;

import com.example.accommodationmanagement.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByApproved(boolean approved);
}
