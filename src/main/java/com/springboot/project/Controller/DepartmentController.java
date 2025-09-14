package com.springboot.project.Controller;

import com.springboot.project.dto.AddNewDeptDTO;
import com.springboot.project.dto.DepartmentDTO;
import com.springboot.project.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // URL : http://localhost:8080/department/.....
    @PostMapping(value = "/add/department")
    public DepartmentDTO addNewDepartment(@RequestBody AddNewDeptDTO addNewDeptDTO){
        return departmentService.addNewDepartment(addNewDeptDTO);
    }

    @GetMapping(value = "/show/departments")
    public List<DepartmentDTO> showAllDepartment(){
        return departmentService.showAllDepartment();
    }

}
