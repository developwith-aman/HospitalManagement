package com.springboot.project.service;

import com.springboot.project.dto.CreateNewAppointment;
import com.springboot.project.entity.Appointment;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {
    Appointment bookPatientAppointment(CreateNewAppointment newAppointment);

    Appointment reassignAppointment(int doctorID, Long appointmentID);

    ResponseEntity<Appointment> deleteAppointment(Long appointmentID, Long patientID);
}
