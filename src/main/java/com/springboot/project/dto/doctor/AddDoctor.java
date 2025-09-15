package com.springboot.project.dto.doctor;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDoctor {

    private String doctorName;
    private String specialization;
    private String email;
}
