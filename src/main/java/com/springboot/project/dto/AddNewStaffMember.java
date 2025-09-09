package com.springboot.project.dto;

import com.springboot.project.entity.staff.StaffDepartments;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddNewStaffMember {

    private String staffName;
    private int age;
    private double salary;
    private StaffDepartments staffDepartments;
}
