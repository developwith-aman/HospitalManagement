package com.springboot.project.Controller;

import com.springboot.project.dto.*;
import com.springboot.project.entity.Appointment;
import com.springboot.project.entity.Insurance;
import com.springboot.project.entity.Patient;
import com.springboot.project.entity.bloodType.BloodGroups;
import com.springboot.project.service.AppointmentService;
import com.springboot.project.service.InsuranceService;
import com.springboot.project.service.PatientService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/patients")
//@RequiredArgsConstructor  -- This could also be used instead of manually creating the required constructor
public class PatientController {

    private final PatientService patientService;
    private final InsuranceService insuranceService;
    private final AppointmentService appointmentService;

    public PatientController(PatientService patientService,
                             InsuranceService insuranceService,
                             AppointmentService appointmentService) {
        this.patientService = patientService;
        this.insuranceService = insuranceService;
        this.appointmentService = appointmentService;
    }


    // URL : http://localhost:8080/patients/.....
    // Fetching all the patients
    @GetMapping(value = "/all/patients")
    public List<PatientsDTO> getAllPatients() {
        return patientService.getAllPatients();
    }


    // Fetching patient with id
    @GetMapping(value = "/patient/{id}")
    public PatientsDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }


    // Fetching patient with their name
    @GetMapping(value = "/name/{patientName}")
    public PatientsDTO findByName(@PathVariable String patientName) {
        return patientService.findByName(patientName);
    }

    // Delete OR Remove the patient with their name
    @DeleteMapping(value = "/discharge/patient/{patientID}")
    public PatientsDTO dischargePatientByName(@PathVariable Long patientID) {
        return patientService.dischargePatientFromHospital(patientID);
    }


    // Fetching all the patients with a same type of blood group
    @GetMapping(value = "/by/blood/group/{group}")
    public List<PatientsDTO> findByBloodGroup(@PathVariable BloodGroups group) {
        return patientService.findByBloodGroup(group);
    }


    // Simple Blood-Group count
    @GetMapping(value = "/count/blood/group")
    public List<Object[]> countBloodGroup() {   // it will return the object array
        return patientService.countBloodGroup();
    }


    // Blood-Group count using projection
    @GetMapping(value = "/blood/group/count/projection")
    public List<BloodGroupCount> countBloodGroupUsingProjection() {
        return patientService.countBloodGroupByProjection();
    }


    // To update the email of the patient
    @PatchMapping(value = "/update/email/{patientId}")
    public PatientsDTO updatePatientEmail(@PathVariable Long patientId, @RequestBody UpdateEmail updateEmail) {
        return patientService.updatePatientEmailById(patientId, updateEmail.getEmail());
    }


    // This ↓ is the manual way of performing paging...
//    @GetMapping(value = "/fetch/all/paged/patients")
//    public Page<Patient> fetchPagedPatients(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "2") int size) {
//        return patientService.getAllPagedPatients(PageRequest.of(page, size));
//    }


    // Spring automatically injects a Pageable object based on request parameters (page, size, sort),
    // so we don't need to manually use @RequestParam. @PageableDefault sets default values
    @GetMapping(value = "/fetch/paged/patients")
    public Page<Patient> fetchPagedPatients(
            @PageableDefault(size = 2, sort = "patientID", direction = Sort.Direction.ASC) Pageable pageable) {
        return patientService.getAllPagedPatients(pageable);
    }


    // Adding a new patient
    @PostMapping(value = "/add/new/patient")
    public PatientsDTO addPatient(@RequestBody AddNewPatient addNewPatient) {
        return patientService.addNewPatient(addNewPatient);
    }


    // Adding the patient with the insurance details
    @PostMapping(value = "/add/insured/patient")
    public PatientsDTO addPatientWithInsurance(@RequestBody AddInsuredPatient addInsuredPatient) {
        return patientService.addInsuredPatient(addInsuredPatient.getPatient(), addInsuredPatient.getInsurance());
    }


    // Adding the insurance details to a patient
    @PostMapping(value = "/assign/insurance/patientID/{patientID}")
    public Patient addInsurance(@RequestBody Insurance insurance, @PathVariable Long patientID) {
        return insuranceService.addInsuranceOfPatient(insurance, patientID);
    }


    // Adding appointment to the patient
    @PostMapping(value = "/book/appointment")
    public AppointmentDTO bookAppointment(@RequestBody CreateNewAppointment newAppointment) {
        return appointmentService.bookPatientAppointment(newAppointment);
    }


    // Delete a particular appointment of a particular patient
    @DeleteMapping(value = "/delete/appointmentID/patientID/{appointmentID}/{patientID}")
    public ResponseEntity<Appointment> deleteAppointment(
            @PathVariable Long appointmentID,
            @PathVariable  Long patientID){
        return appointmentService.deleteAppointment(appointmentID, patientID);
    }
}
