package com.springboot.project.service;

import com.springboot.project.dto.AddDoctorsToDeptDTO;
import com.springboot.project.dto.AddNewDeptDTO;
import com.springboot.project.dto.ApiResponseForDoctorToDept;
import com.springboot.project.dto.DepartmentDTO;
import com.springboot.project.entity.Doctor;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO addNewDepartment(AddNewDeptDTO addNewDeptDTO);

    List<DepartmentDTO> showAllDepartment();

    ApiResponseForDoctorToDept addDoctorsToDept(AddDoctorsToDeptDTO addDoctorsToDeptDTO);
}
