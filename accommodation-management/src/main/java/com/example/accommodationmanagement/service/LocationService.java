package com.example.accommodationmanagement.service;

import com.example.accommodationmanagement.model.Location;
import com.example.accommodationmanagement.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public List<Location> findByApprovved(boolean approved) {
        return locationRepository.findByApproved(approved);
    }

    public void save(Location location) {
        locationRepository.save(location);
    }

    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
