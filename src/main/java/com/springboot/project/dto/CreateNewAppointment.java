package com.springboot.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewAppointment {

    private int doctorId;
    private Long patientId;
    private LocalDateTime appointment_time;
    private String reason;
}
