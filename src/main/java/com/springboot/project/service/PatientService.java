package com.springboot.project.service;

import com.springboot.project.dto.AddNewPatient;
import com.springboot.project.dto.BloodGroupCount;
import com.springboot.project.dto.PatientsDTO;
import com.springboot.project.entity.Insurance;
import com.springboot.project.entity.Patient;
import com.springboot.project.entity.bloodType.BloodGroups;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {

    PatientsDTO getPatientById(Long id);

    List<PatientsDTO> getAllPatients();

    PatientsDTO addNewPatient(AddNewPatient addNewPatient);

    PatientsDTO findByName(String patientName);

    List<PatientsDTO> findByBloodGroup(BloodGroups group);

    List<Object[]> countBloodGroup();

    List<BloodGroupCount> countBloodGroupByProjection();

    PatientsDTO updatePatientEmailById(Long id, String updateEmail);

    Page<Patient> getAllPagedPatients(Pageable pageable);

    PatientsDTO addInsuredPatient(AddNewPatient addInsuredPatient, Insurance insurance);
}
