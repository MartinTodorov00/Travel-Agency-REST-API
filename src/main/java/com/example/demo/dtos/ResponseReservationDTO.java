package com.example.demo.dtos;

import lombok.Data;

@Data
public class ResponseReservationDTO {
    private int id;
    private String contactName;
    private String phoneNumber;
    private ResponseHolidayDTO holiday;

    public ResponseReservationDTO(int id, String contactName, String phoneNumber) {
        this.id = id;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    public ResponseReservationDTO(int id, String contactName, String phoneNumber, ResponseHolidayDTO holiday) {
        this.id = id;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.holiday = holiday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ResponseHolidayDTO getHoliday() {
        return holiday;
    }

    public void setHoliday(ResponseHolidayDTO holiday) {
        this.holiday = holiday;
    }
}

