package com.springboot.project.service;

import com.springboot.project.entity.Insurance;
import com.springboot.project.entity.Patient;

public interface InsuranceService {


    Patient provideInsuranceToPatient(Insurance insurance, Long id);
}
