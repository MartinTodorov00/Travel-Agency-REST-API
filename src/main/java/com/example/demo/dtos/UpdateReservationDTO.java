package com.example.demo.dtos;

import lombok.Data;

@Data
public class UpdateReservationDTO {
    private long id;
    private String contactName;
    private String phoneNumber;
    private long holiday;

    public UpdateReservationDTO(long id, String contactName, String phoneNumber, long holiday) {
        this.id = id;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.holiday = holiday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getHoliday() {
        return holiday;
    }

    public void setHoliday(long holiday) {
        this.holiday = holiday;
    }
}
