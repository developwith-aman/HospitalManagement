package com.springboot.project.service;

import com.springboot.project.dto.patient.PatientsDTO;
import com.springboot.project.entity.Insurance;
import org.springframework.stereotype.Service;

@Service
public interface InsuranceService {


    PatientsDTO addInsuranceOfPatient(Insurance insurance, Long id);
}
