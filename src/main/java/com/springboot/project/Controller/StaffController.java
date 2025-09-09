package com.springboot.project.Controller;

import com.springboot.project.dto.AddNewStaffMember;
import com.springboot.project.dto.StaffDTO;
import com.springboot.project.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/hospital")
public class StaffController {

    private final StaffService staffService;


    // URL : http://localhost:8080/hospital/.....
    @PostMapping(value = "/add/staff")
    public StaffDTO addStaff(@RequestBody AddNewStaffMember addNewStaffMember) {
        return staffService.addNewStaff(addNewStaffMember);
    }

    @GetMapping(value = "/fetch/staff")
    public List<StaffDTO> fetchAllStaff() {
        return staffService.getAllStaff();
    }
}
