package com.springboot.project.service;

import com.springboot.project.dto.PatientsDTO;
import com.springboot.project.entity.Insurance;
public interface InsuranceService {


    PatientsDTO addInsuranceOfPatient(Insurance insurance, Long id);
}
