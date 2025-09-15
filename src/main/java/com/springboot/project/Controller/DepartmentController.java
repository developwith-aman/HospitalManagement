package com.springboot.project.Controller;

import com.springboot.project.dto.AddDoctorsToDeptDTO;
import com.springboot.project.dto.AddNewDeptDTO;
import com.springboot.project.dto.ApiResponseForDoctorToDept;
import com.springboot.project.dto.DepartmentDTO;
import com.springboot.project.entity.Doctor;
import com.springboot.project.service.DepartmentService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/show")
    public List<DepartmentDTO> showAllDepartment(){
        return departmentService.showAllDepartment();
    }


    @PostMapping(value = "/add/doctors")
    public ResponseEntity<ApiResponseForDoctorToDept> addDoctorsToDepartment(@RequestBody AddDoctorsToDeptDTO addDoctorsToDeptDTO){
        return ResponseEntity.ok(departmentService.addDoctorsToDept(addDoctorsToDeptDTO));
    }
}
