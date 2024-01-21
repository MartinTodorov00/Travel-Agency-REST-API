package com.example.demo.controllers;

import com.example.demo.dtos.CreateReservationDTO;
import com.example.demo.dtos.UpdateReservationDTO;
import com.example.demo.dtos.ResponseReservationDTO;
import com.example.demo.entities.Reservation;
import com.example.demo.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping()
    public ResponseEntity<Object> saveNewReservation(
            @RequestBody CreateReservationDTO createReservationDTO) {
        reservationService.saveNewReservation(createReservationDTO);
        return new ResponseEntity<>("Saved.", HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<ResponseReservationDTO> updateReservation(
            @RequestBody UpdateReservationDTO updateReservationDTO) {
        return new ResponseEntity<>(reservationService.updateReservation(updateReservationDTO), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<ResponseReservationDTO>> allReservations() {
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id) {
        return new ResponseEntity<>(reservationService.findReservationById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReservationById(@PathVariable int id) {
        reservationService.deleteReservationById(id);
        return new ResponseEntity<>(id + " deleted.", HttpStatus.OK);

    }

}
