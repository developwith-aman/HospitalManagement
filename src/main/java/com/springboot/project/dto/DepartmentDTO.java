package com.springboot.project.dto;


import com.springboot.project.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Long departmentID;
    private String departmentCode;
    private String departmentName;
    private int headDoctorID;
}
