package com.webtracker.service;

import com.webtracker.entity.Location;
import com.webtracker.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;


    public void saveLocation(Location location) {

        locationRepository.save(location);
    }
    public Location getLocation(String email) {

        return locationRepository.findByUserEmail(email);
    }

}

