package com.springboot.project.service;

import com.springboot.project.dto.AddNewStaffMember;
import com.springboot.project.dto.PatientsDTO;
import com.springboot.project.dto.StaffDTO;

import java.util.List;

public interface StaffService {

    List<StaffDTO> getAllStaff();

    StaffDTO addNewStaff(AddNewStaffMember addNewStaffMember);
}
