package com.springboot.project.Controller;

import com.springboot.project.dto.*;
import com.springboot.project.entity.Insurance;
import com.springboot.project.entity.Patient;
import com.springboot.project.entity.bloodType.BloodGroups;
import com.springboot.project.service.InsuranceService;
import com.springboot.project.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    private final PatientService patientService;
    private final InsuranceService insuranceService;

    public PatientController(PatientService patientService, InsuranceService insuranceService) {
        this.patientService = patientService;
        this.insuranceService = insuranceService;
    }

    @GetMapping(value = "/all/patients")
    public List<PatientsDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping(value = "/patient{id}")
    public PatientsDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping(value = "/name/{patientName}")
    public PatientsDTO findByName(@PathVariable String patientName) {
        return patientService.findByName(patientName);
    }

    // Fetching all the patients with a same blood group
    @GetMapping(value = "/bloodgroup/{group}")
    public List<PatientsDTO> findByBloodGroup(@PathVariable BloodGroups group) {
        return patientService.findByBloodGroup(group);
    }

    @GetMapping(value = "/groupcount")
    public List<Object[]> countBloodGroup() {
        return patientService.countBloodGroup();
    }

    @GetMapping(value = "/group-count-projection")
    public List<BloodGroupCount> countBloodGroupUsingProjection() {
        return patientService.countBloodGroupByProjection();
    }

    @PatchMapping(value = "/update-email/{id}")
    public PatientsDTO updatePatientEmail(@PathVariable Long id, @RequestBody UpdateEmail updateEmail) {
        return patientService.updatePatientEmailById(id, updateEmail.getEmail());
    }


    // This ↓ is the manual way of performing paging...
//    @GetMapping(value = "/fetch-all-paged-patients")
//    public Page<Patient> fetchPagedPatients(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "2") int size) {
//        return patientService.getAllPagedPatients(PageRequest.of(page, size));
//    }


    // Spring automatically injects a Pageable object based on request parameters (page, size, sort),
    // so we don't need to manually use @RequestParam. @PageableDefault sets default values
    @GetMapping(value = "/fetch-all-paged-patients")
    public Page<Patient> fetchPagedPatients(
            @PageableDefault(size = 2, sort = "patientID", direction = Sort.Direction.ASC) Pageable pageable) {
        return patientService.getAllPagedPatients(pageable);
    }

    @PostMapping(value = "/newpatient")
    public PatientsDTO addPatient(@RequestBody AddNewPatient addNewPatient) {
        return patientService.addNewPatient(addNewPatient);
    }

    @PostMapping(value = "/add/insured-patient")
    public PatientsDTO addPatientWithInsurance(@RequestBody AddInsuredPatient addInsuredPatient) {
        return patientService.addInsuredPatient(addInsuredPatient.getPatient(), addInsuredPatient.getInsurance());
    }

    @PostMapping(value = "/add-insurance/{patientID}")
    public Patient addInsurance(@RequestBody Insurance insurance, @PathVariable Long patientID){
        return insuranceService.provideInsuranceToPatient(insurance, patientID);
    }
}
