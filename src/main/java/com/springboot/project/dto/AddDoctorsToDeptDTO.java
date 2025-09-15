package com.springboot.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDoctorsToDeptDTO {

    private Long departmentID;
    private int doctorID;
}
