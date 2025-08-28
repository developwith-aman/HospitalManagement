package com.springboot.project.service.impls;

import com.springboot.project.dto.AddDoctor;
import com.springboot.project.entity.Doctor;
import com.springboot.project.repository.DoctorRepository;
import com.springboot.project.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor addDoctor(AddDoctor addDoctor) {
        Doctor doctor = new Doctor();
        doctor.setDoctorName(addDoctor.getDoctorName());
        doctor.setSpecialization(addDoctor.getSpecialization());
        doctor.setEmail(addDoctor.getEmail());

        return doctorRepository.save(doctor);
    }
}
