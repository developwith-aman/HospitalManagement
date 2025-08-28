package com.springboot.project.service.impls;

import com.springboot.project.dto.CreateNewAppointment;
import com.springboot.project.entity.Appointment;
import com.springboot.project.entity.Doctor;
import com.springboot.project.entity.Patient;
import com.springboot.project.repository.AppointmentRepository;
import com.springboot.project.repository.DoctorRepository;
import com.springboot.project.repository.PatientRepository;
import com.springboot.project.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    @Override
    public Appointment bookPatientAppointment(CreateNewAppointment newAppointment) {
        System.out.println("Doctor ID = " + newAppointment.getDoctorId());
        System.out.println("Patient ID = " + newAppointment.getPatientId());
        Doctor doctor = doctorRepository.findById(newAppointment.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("No doctor found..."));
        Patient patient = patientRepository.findById(newAppointment.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("No patient found..."));

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointment_time(newAppointment.getAppointment_time());
        appointment.setReason(newAppointment.getReason());

        return appointmentRepository.save(appointment);
    }
}
