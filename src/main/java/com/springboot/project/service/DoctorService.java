package com.springboot.project.service;

import com.springboot.project.dto.AddDoctor;
import com.springboot.project.dto.DoctorDTO;
import com.springboot.project.dto.PatientsDTO;
import com.springboot.project.entity.Doctor;

import javax.print.Doc;


public interface DoctorService {

    DoctorDTO addDoctor(AddDoctor addDoctor);
    DoctorDTO updateDoctorEmailById(int doctorID, String email);
}
