package com.springboot.project.service.impls;

import com.springboot.project.entity.Insurance;
import com.springboot.project.entity.Patient;
import com.springboot.project.repository.InsuranceRepository;
import com.springboot.project.repository.PatientRepository;
import com.springboot.project.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository; // the required constructor will be provided by '@RequiredArgsConstructor'
    private final PatientRepository patientRepository;


    @Override
    @Transactional
    public Patient addInsuranceOfPatient(Insurance insurance, Long patientID){
        Patient patient = patientRepository
                .findById(patientID)
                .orElseThrow(()-> new IllegalArgumentException("No Patient found with id : "+patientID));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        insuranceRepository.save(insurance);
        return patient;
    }
}






