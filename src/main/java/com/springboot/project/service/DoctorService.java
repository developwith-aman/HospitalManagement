package com.springboot.project.service;

import com.springboot.project.dto.AddDoctor;
import com.springboot.project.dto.DoctorDTO;
import com.springboot.project.entity.Doctor;


public interface DoctorService {

    DoctorDTO addDoctor(AddDoctor addDoctor);
}
