package com.example.demo.services;

import com.example.demo.dtos.CreateHolidayDTO;
import com.example.demo.dtos.UpdateHolidayDTO;
import com.example.demo.dtos.ResponseHolidayDTO;
import com.example.demo.entities.Holiday;

import java.util.List;

public interface HolidayService {

    Holiday getHolidayById(int holidayId);

    List<ResponseHolidayDTO> getAllHolidays();

    void saveNewHoliday(CreateHolidayDTO createHolidayRequestDTO);

    ResponseHolidayDTO holidayToHolidayResponseMapper(Holiday holiday);

    Holiday holidayRequestToHolidayMapper(CreateHolidayDTO createHolidayDTO);

    ResponseHolidayDTO updateHoliday(UpdateHolidayDTO updateHolidayDTO);

    void deleteHolidayByID(int id);
}
