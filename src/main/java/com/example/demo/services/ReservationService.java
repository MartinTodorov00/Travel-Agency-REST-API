package com.example.demo.services;

import com.example.demo.dtos.CreateReservationDTO;
import com.example.demo.dtos.UpdateReservationDTO;
import com.example.demo.dtos.ResponseReservationDTO;
import com.example.demo.entities.Reservation;

import java.util.List;

public interface ReservationService {

    void saveNewReservation(CreateReservationDTO createReservationDTO);

    Reservation updateSavedLocation(Reservation reservation, UpdateReservationDTO updateReservationDTO);

    ResponseReservationDTO updateReservation(UpdateReservationDTO updateReservationDTO);

    List<ResponseReservationDTO> getAllReservations();

    Reservation findReservationById(int id);

    Reservation findReservationByPhoneNumber(String phoneNumber);

    ResponseReservationDTO reservationToReservationResponseMapper(Reservation reservation);

    Reservation reservationRequestToReservationMapper(CreateReservationDTO createReservationDTO);

    void deleteReservationById(int id);
}
