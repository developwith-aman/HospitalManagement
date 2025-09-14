package com.springboot.project.service.impls;

import com.springboot.project.dto.AddNewDeptDTO;
import com.springboot.project.dto.DepartmentDTO;
import com.springboot.project.entity.Department;
import com.springboot.project.entity.Doctor;
import com.springboot.project.repository.DepartmentRepository;
import com.springboot.project.repository.DoctorRepository;
import com.springboot.project.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    private final DoctorRepository doctorRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper, DoctorRepository doctorRepository) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
        this.doctorRepository = doctorRepository;
    }


    // Add new department
    public DepartmentDTO addNewDepartment(AddNewDeptDTO addNewDeptDTO) {
        Doctor doctor = doctorRepository.findById(addNewDeptDTO.getDepartmentHeadId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (departmentRepository.existsByDepartmentName(addNewDeptDTO.getDepartmentName())) {
            throw new IllegalArgumentException("Department already present, can't add again...");
        }

        Department department = new Department();
        department.setDepartmentName(addNewDeptDTO.getDepartmentName());
        department.setHeadDoctor(doctor);

        // Save once to get ID
        departmentRepository.save(department);

        // Generate and set department code
        String departmentCode = generateDepartmentCode(department.getDepartmentName(), department.getDepartmentID());
        department.setDepartmentCode(departmentCode);

        // Save once more with updated code
        Department savedDepartment = departmentRepository.save(department);

        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    public String generateDepartmentCode(String departmentName, Long id) {
        String prefix = departmentName.substring(0, 3).toUpperCase();
        return prefix + String.format("%03d", id);
    }


    // To fetch all departments
    @Override
    public List<DepartmentDTO> showAllDepartment() {
        List<Department> department = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (Department dept : department){
            departmentDTOList.add(modelMapper.map(dept, DepartmentDTO.class));
        }
        return departmentDTOList;
    }
}
