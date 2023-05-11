package com.unicomer.desafio.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicomer.desafio.model.HolidayResponse;

import jakarta.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class HolidayService {

    private static final Logger logger = LogManager.getLogger(HolidayService.class);

    private HolidayResponse holidayResponse = new HolidayResponse();

    @PostConstruct
    public void init() {
        this.holidayResponse = getHolidays();
    }

    public HolidayResponse getHolidays() {

        logger.info("Realizar la llamada al servicio para obtener los feriados");
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://api.victorsanmartin.com/feriados/en.json", String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        HolidayResponse holidayResponse = new HolidayResponse();
        try {
            logger.info("Iniciando invocacion" + response);
            holidayResponse = objectMapper.readValue(response, HolidayResponse.class);
        } catch (Exception e) {
            holidayResponse.setStatus("ERROR AL MAPEAR EL JSON");
            logger.error("Error al mapear el JSON" + e.getMessage());
        }

        return holidayResponse;

    }

}
