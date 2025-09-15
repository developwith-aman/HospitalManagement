package com.springboot.project.service;

import com.springboot.project.dto.staff.AddNewStaffMember;
import com.springboot.project.dto.staff.StaffDTO;

import java.util.List;

public interface StaffService {

    List<StaffDTO> getAllStaff();

    StaffDTO addNewStaff(AddNewStaffMember addNewStaffMember);
}
