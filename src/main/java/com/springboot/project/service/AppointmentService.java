package com.springboot.project.service;

import com.springboot.project.dto.CreateNewAppointment;
import com.springboot.project.entity.Appointment;

public interface AppointmentService {
    Appointment bookPatientAppointment(CreateNewAppointment newAppointment);
}
