package com.unicomer.desafio.controller;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicomer.desafio.model.Data;
import com.unicomer.desafio.model.DateRange;
import com.unicomer.desafio.model.HolidayResponse;
import com.unicomer.desafio.services.HolidayService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/holiday")
public class HolidayController {

    private static final Logger logger = LogManager.getLogger(HolidayController.class);

    private HolidayService holidayService = new HolidayService();

    public HolidayController(HolidayService holidayService2) {
    }

    @GetMapping("/getAllData")
    @Operation(summary = "Get all data", description = "Returns a list of all data")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public HolidayResponse getAllData() {
        return holidayService.getHolidays();
    }

    @GetMapping("/filter-by-title")
    @Operation(summary = "filter-by-title", description = "Returns a list by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    public List<Data> filterByTitle(@RequestParam String title) {

        List<Data> data = holidayService.getHolidays().getData();

        List<Data> filteredData = data.stream()
                .filter(h -> h.getTitle().contains(title))
                .collect(Collectors.toList());
        return filteredData;
    }

    @GetMapping("/filter-by-type")
    @Operation(summary = "filter-by-type", description = "Returns a list by type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    public List<Data> filterByType(@RequestParam String type) {

        List<Data> data = holidayService.getHolidays().getData();

        List<Data> filteredData = data.stream()
                .filter(h -> h.getType().contains(type))
                .collect(Collectors.toList());
        return filteredData;
    }

    @GetMapping("/filter-by-extra")
    @Operation(summary = "filter-by-extra", description = "Returns a list by extra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    public List<Data> filterByExtra(@RequestParam String extra) {

        List<Data> data = holidayService.getHolidays().getData();

        List<Data> filteredData = data.stream()
                .filter(h -> h.getExtra().contains(extra))
                .collect(Collectors.toList());
        return filteredData;
    }

    @GetMapping("/filter-by-date")
    @Operation(summary = "Filter data by date", description = "Returns a list of data that match the given date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    public List<Data> filterByEDate(@RequestParam String date) {

        List<Data> data = holidayService.getHolidays().getData();

        List<Data> filteredData = data.stream()
                .filter(h -> h.getDate().contains(date))
                .collect(Collectors.toList());
        return filteredData;
    }

    @GetMapping("/filter-by-date-range")
    @Operation(summary = "Filter data by date range", description = "Returns a list of data that match the given date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid date format"),
            @ApiResponse(responseCode = "404", description = "No results found")
    })
    public ResponseEntity<List<Data>> filterByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        List<Data> data = holidayService.getHolidays().getData();

        List<Data> filteredData = data.stream()
                .filter(h -> {
                    LocalDate holidayDate = LocalDate.parse(h.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
                    return holidayDate.compareTo(startDate) >= 0 && holidayDate.compareTo(endDate) <= 0;
                })
                .collect(Collectors.toList());

        if (filteredData.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(filteredData);
        }
    }

}
