package com.springboot.project.service;

import com.springboot.project.dto.AddNewDeptDTO;
import com.springboot.project.dto.DepartmentDTO;

public interface DepartmentService {

    DepartmentDTO addNewDepartment(AddNewDeptDTO addNewDeptDTO);
}
