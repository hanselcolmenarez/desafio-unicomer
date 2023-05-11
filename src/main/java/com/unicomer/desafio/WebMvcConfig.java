package com.unicomer.desafio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private DateRangeConverter dateRangeConverter;
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2XmlHttpMessageConverter());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateRangeConverter);
    }
}
