package com.example.demo.services;

import com.example.demo.repositories.HolidayRepository;
import com.example.demo.repositories.LocationRepository;
import com.example.demo.dtos.CreateHolidayDTO;
import com.example.demo.dtos.UpdateHolidayDTO;
import com.example.demo.dtos.ResponseHolidayDTO;
import com.example.demo.entities.Holiday;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {
    private final HolidayRepository holidayRepository;

    private LocationRepository locationRepository;
    private LocationService locationService;

    public HolidayServiceImpl(HolidayRepository holidayRepository, LocationRepository locationRepository, LocationService locationService) {
        this.holidayRepository = holidayRepository;
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }

    @Override
    public Holiday getHolidayById(int holidayId) {
        return holidayRepository.findById(holidayId).get();
    }

    @Override
    public List<ResponseHolidayDTO> getAllHolidays() {
        return holidayRepository.findAll().stream().map(this::holidayToHolidayResponseMapper).toList();
    }

    @Override
    public void saveNewHoliday(CreateHolidayDTO createHolidayRequestDTO) {
        Holiday holidayToSave = holidayRequestToHolidayMapper(createHolidayRequestDTO);
        holidayRepository.save(holidayToSave);
    }

    @Override
    public ResponseHolidayDTO holidayToHolidayResponseMapper(Holiday holiday) {
        return new ResponseHolidayDTO(holiday.getId(), locationService.locationToLocationResponseMapper(locationService.findLocationEntityById((int) holiday.getLocation())), holiday.getTitle(), holiday.getStartDate(), holiday.getDuration(), holiday.getPrice(), holiday.getFreeSlots());
    }

    @Override
    public Holiday holidayRequestToHolidayMapper(CreateHolidayDTO createHolidayDTO) {
        return new Holiday(createHolidayDTO.getLocation(), createHolidayDTO.getTitle(), createHolidayDTO.getStartDate(), createHolidayDTO.getDuration(), createHolidayDTO.getPrice(), createHolidayDTO.getFreeSlots());
    }

    @Override
    public ResponseHolidayDTO updateHoliday(UpdateHolidayDTO updateHolidayDTO) {
        Holiday searchedHoliday = this.getHolidayById(Math.toIntExact(updateHolidayDTO.getId()));
        Holiday updatedHoliday = this.updateSavedLocation(searchedHoliday, updateHolidayDTO);
        return this.holidayToHolidayResponseMapper(updatedHoliday);
    }

    public Holiday updateSavedLocation(Holiday holiday, UpdateHolidayDTO updateHolidayDTO) {
        holiday.setLocation(updateHolidayDTO.getLocation());
        holiday.setTitle(updateHolidayDTO.getTitle());
        holiday.setStartDate(updateHolidayDTO.getStartDate());
        holiday.setDuration(updateHolidayDTO.getDuration());
        holiday.setPrice(updateHolidayDTO.getPrice());
        holiday.setFreeSlots(updateHolidayDTO.getFreeSlots());
        return holidayRepository.save(holiday);
    }

    @Override
    public void deleteHolidayByID(int id) {
        holidayRepository.deleteById(id);
    }
}