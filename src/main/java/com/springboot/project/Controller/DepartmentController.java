package com.springboot.project.Controller;

import com.springboot.project.dto.AddNewDeptDTO;
import com.springboot.project.dto.DepartmentDTO;
import com.springboot.project.service.DepartmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(value = "/add/department")
    public DepartmentDTO addNewDepartment(@RequestBody AddNewDeptDTO addNewDeptDTO){
        return departmentService.addNewDepartment(addNewDeptDTO);
    }

}
