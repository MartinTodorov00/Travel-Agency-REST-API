package com.example.demo.services;

import com.example.demo.repositories.LocationRepository;
import com.example.demo.dtos.CreateLocationDTO;
import com.example.demo.dtos.UpdateLocationDTO;
import com.example.demo.dtos.ResponseLocationDTO;
import com.example.demo.entities.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location findLocationEntityById(int id) {
        return locationRepository.findById(id).get();
    }

    @Override
    public void saveNewLocation(CreateLocationDTO createLocationDTO) {
        Location locationToSave = locationRequestToLocationMapper(createLocationDTO);
        locationRepository.save(locationToSave);
    }

    @Override
    public ResponseLocationDTO updateLocation(UpdateLocationDTO updateLocationDTO) {
        Location searchedLocation = this.findLocationEntityById(Math.toIntExact(updateLocationDTO.getId()));
        Location updatedLocation = this.updateSavedLocation(searchedLocation, updateLocationDTO);
        return this.locationToLocationResponseMapper(updatedLocation);
    }

    @Override
    public List<ResponseLocationDTO> getAllLocations() {
        return locationRepository.findAll().stream().map(this::locationToLocationResponseMapper).toList();
    }

    @Override
    public ResponseLocationDTO locationToLocationResponseMapper(Location location) {
        return new ResponseLocationDTO(location.getId(), location.getStreet(), location.getNumber(), location.getCity(), location.getCountry(), location.getImageUrl());
    }

    @Override
    public Location locationRequestToLocationMapper(CreateLocationDTO createLocationDTO) {
        return new Location(createLocationDTO.getStreet(), createLocationDTO.getNumber(), createLocationDTO.getCity(), createLocationDTO.getCountry(), createLocationDTO.getImageUrl());
    }

    @Override
    public Location updateSavedLocation(Location location, UpdateLocationDTO updateLocationDTO) {
        location.setCity(updateLocationDTO.getCity());
        location.setCountry(updateLocationDTO.getCountry());
        location.setNumber(updateLocationDTO.getNumber());
        location.setStreet(updateLocationDTO.getStreet());
        location.setImageUrl(updateLocationDTO.getImageUrl());
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocationById(int id) {
        locationRepository.deleteById(id);
    }
}
