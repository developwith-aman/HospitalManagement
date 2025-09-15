package com.springboot.project.config;

import com.springboot.project.dto.patient.PatientsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PatientsDTO patientsDTO(){
        return new PatientsDTO();
    }
}
