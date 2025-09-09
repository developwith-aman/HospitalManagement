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
import org.aspectj.asm.IModelFilter;
import org.springframework.http.ResponseEntity;
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

    @Transactional
    @Override
    public Appointment reassignAppointment(int doctorID, Long appointmentID) {
        Appointment newAppointment = appointmentRepository
                .findById(appointmentID)
                .orElseThrow(()-> new IllegalArgumentException("No appointment found..."));
        Doctor doctor = doctorRepository
                .findById(doctorID)
                .orElseThrow(()-> new IllegalArgumentException("No doctor found..."));

        newAppointment.setDoctor(doctor);
        return newAppointment;
    }

    @Transactional
    @Override
    public ResponseEntity<Appointment> deleteAppointment(Long appointmentID, Long patientID) {
        Patient patient = patientRepository.findById(patientID)
                .orElseThrow(()-> new IllegalArgumentException("No patient found..."));
        Appointment appointment = appointmentRepository.findById(appointmentID).
                orElseThrow(()-> new IllegalArgumentException("No Appointment found..."));
        if (appointment.getPatient().getPatientID().equals(patientID)) {
            patient.getAppointments().remove(appointment);
            appointment.setPatient(null); // orphan remove will work here....
//            appointmentRepository.delete(appointment);  // since 'orphanRemoval = true' no need to delete explicitly
        }
        return ResponseEntity.ok(appointment);
    }


}
