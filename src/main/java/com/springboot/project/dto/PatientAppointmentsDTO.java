package com.springboot.project.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PatientAppointmentsDTO {

    private int numberOfAppointments;
    private int doctorId;
    private String reason;

}
