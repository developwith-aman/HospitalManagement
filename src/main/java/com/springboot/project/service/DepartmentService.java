package com.springboot.project.service;

import com.springboot.project.dto.department.Doctor_DepartmentDTO;
import com.springboot.project.dto.department.AddNewDeptDTO;
import com.springboot.project.dto.ApiResponse;
import com.springboot.project.dto.department.DepartmentDTO;
import com.springboot.project.dto.doctor.DoctorDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO addNewDepartment(AddNewDeptDTO addNewDeptDTO);

    List<DepartmentDTO> showAllDepartment();

    ApiResponse addDoctorsToDept(Doctor_DepartmentDTO doctorDepartmentDTO);

    List<DoctorDTO> showDepartmentDoctors(Long departmentID);
}
