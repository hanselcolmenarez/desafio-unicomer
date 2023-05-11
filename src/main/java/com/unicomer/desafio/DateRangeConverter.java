package com.unicomer.desafio;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.unicomer.desafio.model.DateRange;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class DateRangeConverter implements Converter<String, DateRange> {

    @Override
    public DateRange convert(String source) {
        String[] dateRange = source.split(",");
        if (dateRange.length != 2) {
            throw new IllegalArgumentException("Invalid date range format. Expected format is yyyy-MM-dd,yyyy-MM-dd");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dateRange[0].trim(), formatter);
        LocalDate endDate = LocalDate.parse(dateRange[1].trim(), formatter);
        return new DateRange(startDate, endDate);
    }
}