package com.springboot.project.dto;

import com.springboot.project.entity.staff.StaffDepartment;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddNewStaffMember {

    private String staffName;
    private int age;
    private double salary;
    private StaffDepartment staffDepartment;
}
