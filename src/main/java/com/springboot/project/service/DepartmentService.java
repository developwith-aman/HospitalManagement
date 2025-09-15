package com.springboot.project.service;

import com.springboot.project.dto.department.Doctor_DepartmentDTO;
import com.springboot.project.dto.department.AddNewDeptDTO;
import com.springboot.project.dto.ApiResponse;
import com.springboot.project.dto.department.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO addNewDepartment(AddNewDeptDTO addNewDeptDTO);

    List<DepartmentDTO> showAllDepartment();

    ApiResponse addDoctorsToDept(Doctor_DepartmentDTO doctorDepartmentDTO);
}
