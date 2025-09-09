package com.springboot.project.service.impls;

import com.springboot.project.dto.AddNewStaffMember;
import com.springboot.project.dto.StaffDTO;
import com.springboot.project.entity.Staff;
import com.springboot.project.repository.StaffRepository;
import com.springboot.project.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final ModelMapper modelMapper;

    public StaffServiceImpl(StaffRepository staffRepository, ModelMapper modelMapper) {
        this.staffRepository = staffRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        List<Staff> allStaff = staffRepository.findAll();
        List<StaffDTO> staffDTOList = new ArrayList<>();
        for (Staff staff : allStaff) {
            StaffDTO staffDTO = modelMapper.map(staff, StaffDTO.class);
            staffDTOList.add(staffDTO);
        }
        return staffDTOList;
    }

    @Override
    public StaffDTO addNewStaff(AddNewStaffMember addNewStaffMember) {
        Staff newStaff = new Staff();
        newStaff.setStaffName(addNewStaffMember.getStaffName());
        newStaff.setAge(addNewStaffMember.getAge());
        newStaff.setSalary(addNewStaffMember.getSalary());
        newStaff.setStaffDepartment(addNewStaffMember.getStaffDepartment());

        Staff newStaffAdded = staffRepository.save(newStaff);
        return modelMapper.map(newStaffAdded, StaffDTO.class);
    }
}























