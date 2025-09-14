package com.springboot.project.service;

import com.springboot.project.dto.AddNewDeptDTO;
import com.springboot.project.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO addNewDepartment(AddNewDeptDTO addNewDeptDTO);

    List<DepartmentDTO> showAllDepartment();
}
