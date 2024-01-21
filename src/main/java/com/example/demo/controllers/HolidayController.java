package com.example.demo.controllers;

import com.example.demo.dtos.CreateHolidayDTO;
import com.example.demo.dtos.UpdateHolidayDTO;
import com.example.demo.dtos.ResponseHolidayDTO;
import com.example.demo.entities.Holiday;
import com.example.demo.services.HolidayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holidays")
public class HolidayController {

    private HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @PostMapping()
    public ResponseEntity<Object> saveNewHoliday(
            @RequestBody CreateHolidayDTO createHolidayDTO) {
        holidayService.saveNewHoliday(createHolidayDTO);
        return new ResponseEntity<>("Saved.", HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ResponseHolidayDTO> updateHoliday(
            @RequestBody UpdateHolidayDTO updateHolidayDTO) {
        return new ResponseEntity<>(holidayService.updateHoliday(updateHolidayDTO), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<ResponseHolidayDTO>> getAllHolidays() {
        return new ResponseEntity<>(holidayService.getAllHolidays(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Holiday> getLocationById(@PathVariable int id) {
        return new ResponseEntity<>(holidayService.getHolidayById(id), HttpStatus.OK);
    }


   @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable int id) {
        holidayService.deleteHolidayByID(id);
        return new ResponseEntity<>(id + " deleted.", HttpStatus.OK);

    }

}
