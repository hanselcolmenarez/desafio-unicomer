package com.unicomer.desafio.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicomer.desafio.model.Holiday;
import com.unicomer.desafio.model.HolidayResponse;
import com.unicomer.desafio.services.HolidayServiceInitializer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/v1/holidays")
public class HolidayController {

    private static final Logger logger = LogManager.getLogger(HolidayController.class);

    private HolidayServiceInitializer holidayServiceInitializer;

    @GetMapping("/getAllHolidays")
    @Operation(summary = "Get all holidays", description = "Returns a list of all holidays")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public HolidayResponse getAllHolidays() {
        holidayServiceInitializer = new HolidayServiceInitializer();
        return holidayServiceInitializer.getHolidays();
    }

    @GetMapping("/filter-by-title")
    @Operation(summary = "filter-by-title", description = "Returns a list by title")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    public List<Holiday> filterByTitle(@RequestParam String title) {

        HolidayResponse holidayResponse = new HolidayResponse();

        holidayServiceInitializer = new HolidayServiceInitializer();

        holidayResponse = holidayServiceInitializer.getHolidays();

        List<Holiday> holidays = holidayResponse.getHolidays();

        List<Holiday> filteredHolidays = holidays.stream()
                .filter(h -> h.getTitle().contains(title))
                .collect(Collectors.toList());
        return filteredHolidays;
    }

    @GetMapping("/filter-by-type")
    @Operation(summary = "filter-by-type", description = "Returns a list by type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    public List<Holiday> filterByType(@RequestParam String type) {

        HolidayResponse holidayResponse = new HolidayResponse();

        holidayServiceInitializer = new HolidayServiceInitializer();

        holidayResponse = holidayServiceInitializer.getHolidays();

        List<Holiday> holidays = holidayResponse.getHolidays();

        List<Holiday> filteredHolidays = holidays.stream()
                .filter(h -> h.getType().contains(type))
                .collect(Collectors.toList());
        return filteredHolidays;
    }

    @GetMapping("/filter-by-extra")
    @Operation(summary = "filter-by-extra", description = "Returns a list by extra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    public List<Holiday> filterByExtra(@RequestParam String extra) {

        HolidayResponse holidayResponse = new HolidayResponse();

        holidayServiceInitializer = new HolidayServiceInitializer();

        holidayResponse = holidayServiceInitializer.getHolidays();

        List<Holiday> holidays = holidayResponse.getHolidays();

        List<Holiday> filteredHolidays = holidays.stream()
                .filter(h -> h.getExtra().contains(extra))
                .collect(Collectors.toList());
        return filteredHolidays;
    }


    @GetMapping("/filter-by-date")
    @Operation(summary = "Filter holidays by date", description = "Returns a list of holidays that match the given date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    public List<Holiday> filterByEDate(@RequestParam String date) {

        HolidayResponse holidayResponse = new HolidayResponse();

        holidayServiceInitializer = new HolidayServiceInitializer();

        holidayResponse = holidayServiceInitializer.getHolidays();

        List<Holiday> holidays = holidayResponse.getHolidays();

        List<Holiday> filteredHolidays = holidays.stream()
                .filter(h -> h.getDate().contains(date))
                .collect(Collectors.toList());
        return filteredHolidays;
    }

}
