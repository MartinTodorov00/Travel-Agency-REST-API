package com.example.demo.services;

import com.example.demo.repositories.ReservationRepository;
import com.example.demo.dtos.CreateReservationDTO;
import com.example.demo.dtos.UpdateReservationDTO;
import com.example.demo.dtos.ResponseReservationDTO;
import com.example.demo.entities.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    private HolidayService holidayService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, HolidayService holidayService) {
        this.reservationRepository = reservationRepository;
        this.holidayService = holidayService;
    }

    @Override
    public void saveNewReservation(CreateReservationDTO createReservationDTO) {
        Reservation reservationToSave = reservationRequestToReservationMapper(createReservationDTO);
        reservationRepository.save(reservationToSave);
    }

    @Override
    public Reservation updateSavedLocation(Reservation reservation, UpdateReservationDTO updateReservationDTO) {
        reservation.setContactName(updateReservationDTO.getContactName());
        reservation.setPhoneNumber(updateReservationDTO.getPhoneNumber());
        reservation.setHoliday(updateReservationDTO.getHoliday());
        return reservationRepository.save(reservation);
    }

    @Override
    public ResponseReservationDTO updateReservation(UpdateReservationDTO updateReservationDTO) {
        Reservation searchedReservation = this.findReservationById(Math.toIntExact(updateReservationDTO.getId()));
        Reservation updatedReservation = this.updateSavedLocation(searchedReservation, updateReservationDTO);
        return this.reservationToReservationResponseMapper(updatedReservation);
    }

    @Override
    public List<ResponseReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream().map(this::reservationToReservationResponseMapper).toList();
    }

    @Override
    public Reservation findReservationById(int id) {
        return reservationRepository.findById(id).get();
    }

    @Override
    public ResponseReservationDTO reservationToReservationResponseMapper(Reservation reservation) {
        return new ResponseReservationDTO(reservation.getId(), reservation.getContactName(), reservation.getPhoneNumber(), holidayService.holidayToHolidayResponseMapper(holidayService.getHolidayById((int) reservation.getHoliday())));
    }

    @Override
    public Reservation reservationRequestToReservationMapper(CreateReservationDTO createReservationDTO) {
        return new Reservation(createReservationDTO.getContactName(), createReservationDTO.getPhoneNumber(), createReservationDTO.getHoliday());
    }

    @Override
    public Reservation findReservationByPhoneNumber(String phoneNumber) {
        return reservationRepository.findReservationByPhoneNumber(phoneNumber);
    }

    @Override
    public void deleteReservationById(int id) {
        reservationRepository.deleteById(id);
    }
}
