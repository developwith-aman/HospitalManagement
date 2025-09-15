package com.springboot.project.service;

import com.springboot.project.dto.doctor.AddDoctor;
import com.springboot.project.dto.doctor.DoctorDTO;


public interface DoctorService {

    DoctorDTO addDoctor(AddDoctor addDoctor);
    DoctorDTO updateDoctorEmailById(int doctorID, String email);
}
