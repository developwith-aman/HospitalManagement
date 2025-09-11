package com.springboot.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class AppointmentDTO {

    private Long appointmentId;
    private int doctorId;
    private Long patientId;
    private LocalDateTime appointment_time;
    private String reason;
    private int numberOfAppointments;
}
