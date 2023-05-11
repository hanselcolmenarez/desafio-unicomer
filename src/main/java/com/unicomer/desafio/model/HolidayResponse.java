package com.unicomer.desafio.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HolidayResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private List<Holiday> holidays;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<Holiday> getHolidays() {
        return holidays;
    }
    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }

    
    
}