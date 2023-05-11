package com.unicomer.desafio;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.unicomer.desafio.controller.HolidayController;
import com.unicomer.desafio.model.Data;
import com.unicomer.desafio.model.HolidayResponse;
import com.unicomer.desafio.services.HolidayService;



@RunWith(SpringRunner.class)
@WebMvcTest(HolidayController.class)
public class HolidayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HolidayService holidayService;

    @InjectMocks
    private HolidayController holidayController;

    private List<Data> testData;

    @BeforeEach
    public void setUp() {
        holidayService = Mockito.mock(HolidayService.class);
        holidayController = new HolidayController(holidayService);
        testData = Arrays.asList(
                new Data("2023-01-01", "Año Nuevo", "Civil", true, "Civil e Irrenunciable"),
                new Data("2023-01-02", "Feriado Adicional", "Civil", false, "Civil"),
                new Data("2023-04-07", "Viernes Santo", "Religioso", false, "Religioso")
        );
    }


    @Test
    public void testGetAllData() {
        // Given
        Data holiday1 = new Data("2023-01-01", "Año Nuevo", "Civil", true, "Civil e Irrenunciable");
        Data holiday2 = new Data("2023-01-02", "Feriado Adicional", "Civil", false, "Civil");
        HolidayResponse holidayResponse = new HolidayResponse(Arrays.asList(holiday1, holiday2));
        when(holidayService.getHolidays()).thenReturn(holidayResponse);

        // When
        HolidayResponse result = holidayController.getAllData();

        // Then
        assertEquals(17, result.getData().size());
    }

    @Test
    public void testFilterByType() {
        // Given
        String type = "Civil";
        List<Data> data = new ArrayList<>();
        data.add(new Data("2023-01-01", "Año Nuevo", "Civil", true, "Civil e Irrenunciable"));
        data.add(new Data("2023-03-24", "Viernes Santo", "Religioso", true, "Religioso e Irrenunciable"));
        data.add(new Data("2023-05-01", "Día del Trabajador", "Civil", true, "Civil e Irrenunciable"));

        Mockito.when(holidayService.getHolidays()).thenReturn(new HolidayResponse(data));

        // When
        List<Data> result = holidayController.filterByType(type);

        // Then
        Assertions.assertEquals(8, result.size());
        Assertions.assertEquals("Civil", result.get(0).getType());
        Assertions.assertEquals("Civil", result.get(1).getType());
    }

    
    @Test
    public void testFilterByDate() {
        List<Data> filteredData = holidayController.filterByEDate("2023-12-25");
        Assertions.assertEquals(1, filteredData.size());

        Data christmasData = filteredData.get(0);
        Assertions.assertEquals("2023-12-25", christmasData.getDate());
        Assertions.assertEquals("Navidad", christmasData.getTitle());
        Assertions.assertEquals("Religioso", christmasData.getType());
        Assertions.assertEquals(true, christmasData.isInalienable());
        Assertions.assertEquals("Religioso e Irrenunciable", christmasData.getExtra());
    }

    @Test
    public void testFilterByTitle() {
        String title = "Viernes Santo";
        HolidayResponse holidayResponse = new HolidayResponse(testData);
        when(holidayService.getHolidays()).thenReturn(holidayResponse);
        List<Data> filteredData = holidayController.filterByTitle(title);
        assertThat(filteredData, hasSize(1));
        assertThat(filteredData.get(0).getTitle(), is(title));
    }

}
