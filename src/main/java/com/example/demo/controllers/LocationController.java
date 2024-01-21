package com.example.demo.controllers;

import com.example.demo.dtos.CreateLocationDTO;
import com.example.demo.dtos.UpdateLocationDTO;
import com.example.demo.dtos.ResponseLocationDTO;
import com.example.demo.entities.Location;
import com.example.demo.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping()
    public ResponseEntity<Object> saveNewLocation(
            @RequestBody CreateLocationDTO createLocationDTO) {
        locationService.saveNewLocation(createLocationDTO);
        return new ResponseEntity<>("Saved.", HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ResponseLocationDTO> updateLocation(
            @RequestBody UpdateLocationDTO updateLocationDTO) {
        return new ResponseEntity<>(locationService.updateLocation(updateLocationDTO), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<ResponseLocationDTO>> allLocations() {
        return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(
            @PathVariable int id) {
        return new ResponseEntity<>(locationService.findLocationEntityById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLocation(@PathVariable int id) {
    locationService.deleteLocationById(id);
        return new ResponseEntity<>(id + " deleted.", HttpStatus.OK);
    }

}
