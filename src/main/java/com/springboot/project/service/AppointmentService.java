package com.springboot.project.service;

import com.springboot.project.dto.appointment.AppointmentDTO;
import com.springboot.project.dto.appointment.CreateNewAppointment;
import com.springboot.project.entity.Appointment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO bookPatientAppointment(CreateNewAppointment newAppointment);

    Appointment reassignAppointment(int doctorID, Long appointmentID);

    ResponseEntity<Appointment> deleteAppointment(Long appointmentID, Long patientID);

    List<Appointment> getAppointmentsOfDoctor(int doctorID);
}
