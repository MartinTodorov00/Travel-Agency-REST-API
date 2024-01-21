package com.example.demo.services;

import com.example.demo.dtos.CreateLocationDTO;
import com.example.demo.dtos.UpdateLocationDTO;
import com.example.demo.dtos.ResponseLocationDTO;
import com.example.demo.entities.Location;

import java.util.List;

public interface LocationService {

    Location findLocationEntityById(int id);

    void saveNewLocation(CreateLocationDTO createLocationDTO);

    ResponseLocationDTO updateLocation(UpdateLocationDTO updateLocationDTO);

    List<ResponseLocationDTO> getAllLocations();

    ResponseLocationDTO locationToLocationResponseMapper(Location location);

    Location locationRequestToLocationMapper(CreateLocationDTO createLocationDTO);

    Location updateSavedLocation(Location location, UpdateLocationDTO updateLocationDTO);

    void deleteLocationById(int id);
}
