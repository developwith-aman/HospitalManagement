package com.springboot.project.service.impls;

import com.springboot.project.dto.AddDoctor;
import com.springboot.project.dto.DoctorDTO;
import com.springboot.project.entity.Doctor;
import com.springboot.project.repository.DoctorRepository;
import com.springboot.project.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.print.Doc;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorDTO addDoctor(AddDoctor addDoctor) {
        Doctor doctor = new Doctor();
        doctor.setDoctorName(addDoctor.getDoctorName());
        doctor.setSpecialization(addDoctor.getSpecialization());
        doctor.setEmail(addDoctor.getEmail());

        doctorRepository.save(doctor);

        return modelMapper.map(doctor, DoctorDTO.class);
    }
}
