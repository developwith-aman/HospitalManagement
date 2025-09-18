package com.springboot.project.Controller;

import com.springboot.project.dto.department.Doctor_DepartmentDTO;
import com.springboot.project.dto.department.AddNewDeptDTO;
import com.springboot.project.dto.ApiResponse;
import com.springboot.project.dto.department.DepartmentDTO;
import com.springboot.project.dto.doctor.DoctorDTO;
import com.springboot.project.service.DepartmentService;
import lombok.extern.java.Log;
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
    public ResponseEntity<ApiResponse> addDoctorsToDepartment(@RequestBody Doctor_DepartmentDTO doctorDepartmentDTO){
        return ResponseEntity.ok(departmentService.addDoctorsToDept(doctorDepartmentDTO));
    }


    // To fetch all doctors of a particular department
    @GetMapping(value = "/show/doctors-of-department/{departmentID}")
    public List<DoctorDTO> showDoctorsOfDepartment(@PathVariable Long departmentID){
        return departmentService.showDepartmentDoctors(departmentID);
    }
}
