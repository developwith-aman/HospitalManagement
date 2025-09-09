package com.springboot.project.dto;

import com.springboot.project.entity.staff.StaffDepartments;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {

    private Long staffID;
    private String staffName;
    private int age;
    private double salary;
    private StaffDepartments staffDepartment;
}
