package com.unicomer.desafio;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.unicomer.desafio.controller.HolidayController;
import com.unicomer.desafio.model.Holiday;
import com.unicomer.desafio.model.HolidayResponse;
import com.unicomer.desafio.services.HolidayServiceInitializer;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(HolidayController.class)
public class HolidayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HolidayServiceInitializer holidayServiceInitializer;

    @Test
    public void testFilterByEDate() throws Exception {
        HolidayResponse mockResponse = new HolidayResponse();
        List<Holiday> mockHolidays = new ArrayList<>();
        Holiday holiday = new Holiday();
        holiday.setDate("2023-06-01");
        mockHolidays.add(new Holiday());
        mockResponse.setHolidays(mockHolidays);

        given(holidayServiceInitializer.getHolidays()).willReturn(mockResponse);

        String date = "2023-06";
        mockMvc.perform(get("/filter-by-date")
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Holiday 2")))
                .andExpect(jsonPath("$[0].date", is("2023-06-01")))
                .andExpect(jsonPath("$[1].name", is("Holiday 3")))
                .andExpect(jsonPath("$[1].date", is("2023-06-02")));
    }

}
