package com.unicomer.desafio.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicomer.desafio.model.HolidayResponse;

@Service
public class HolidayServiceInitializer {



    public HolidayResponse getHolidays() {

       System.out.println("Realizar la llamada al servicio para obtener los feriados");
       RestTemplate restTemplate = new RestTemplate();
       String response = restTemplate.getForObject("https://api.victorsanmartin.com/feriados/en.json", String.class);

       ObjectMapper objectMapper = new ObjectMapper();
       HolidayResponse holidayResponse = new HolidayResponse();
       try {
            System.out.println("Iniciando invocacion" + response);
           holidayResponse = objectMapper.readValue(response, HolidayResponse.class);
       } catch (Exception e) {
           holidayResponse.setStatus("ERROR AL MAPEAR EL JSON");
           System.out.println("Error al mapear el JSON" + e.getMessage());
       }

       return holidayResponse;

    }
    
}
