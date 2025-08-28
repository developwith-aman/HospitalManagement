package com.springboot.project.Controller;

import com.springboot.project.dto.*;
import com.springboot.project.entity.Appointment;
import com.springboot.project.entity.Doctor;
import com.springboot.project.entity.Insurance;
import com.springboot.project.entity.Patient;
import com.springboot.project.entity.bloodType.BloodGroups;
import com.springboot.project.service.AppointmentService;
import com.springboot.project.service.DoctorService;
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
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    public PatientController(PatientService patientService,
                             InsuranceService insuranceService,
                             AppointmentService appointmentService,
                             DoctorService doctorService)
    {
        this.patientService = patientService;
        this.insuranceService = insuranceService;
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }


    // Fetching all the patients
    @GetMapping(value = "/all/patients")
    public List<PatientsDTO> getAllPatients() {
        return patientService.getAllPatients();
    }


    // Fetching patient with id
    @GetMapping(value = "/patient{id}")
    public PatientsDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }


    // Fetching patient with their name
    @GetMapping(value = "/name/{patientName}")
    public PatientsDTO findByName(@PathVariable String patientName) {
        return patientService.findByName(patientName);
    }


    // Fetching all the patients with a same blood group
    @GetMapping(value = "/blood-group/{group}")
    public List<PatientsDTO> findByBloodGroup(@PathVariable BloodGroups group) {
        return patientService.findByBloodGroup(group);
    }


    // Simple Blood-Group count
    @GetMapping(value = "/count/blood-group")
    public List<Object[]> countBloodGroup() {
        return patientService.countBloodGroup();
    }


    // Blood-Group count using projection
    @GetMapping(value = "/group-count-projection")
    public List<BloodGroupCount> countBloodGroupUsingProjection() {
        return patientService.countBloodGroupByProjection();
    }


    // To update the email of the patient
    @PatchMapping(value = "/update-email/{patientId}")
    public PatientsDTO updatePatientEmail(@PathVariable Long patientId, @RequestBody UpdateEmail updateEmail) {
        return patientService.updatePatientEmailById(patientId, updateEmail.getEmail());
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


    // Adding a new patient
    @PostMapping(value = "/add/new-patient")
    public PatientsDTO addPatient(@RequestBody AddNewPatient addNewPatient) {
        return patientService.addNewPatient(addNewPatient);
    }


    // Adding the patient with the insurance details
    @PostMapping(value = "/add/insured-patient")
    public PatientsDTO addPatientWithInsurance(@RequestBody AddInsuredPatient addInsuredPatient) {
        return patientService.addInsuredPatient(addInsuredPatient.getPatient(), addInsuredPatient.getInsurance());
    }


    // Adding the insurance details to a patient
    @PostMapping(value = "/add-insurance-to/{patientID}")
    public Patient addInsurance(@RequestBody Insurance insurance, @PathVariable Long patientID) {
        return insuranceService.provideInsuranceToPatient(insurance, patientID);
    }

    @PostMapping(value = "/book/appointment")
    public Appointment bookAppointment(@RequestBody CreateNewAppointment newAppointment) {
        return appointmentService.bookPatientAppointment(newAppointment);
    }

    @PostMapping(value = "/add/doctor")
    public Doctor addDoctor(@RequestBody AddDoctor addDoctor){
        return doctorService.addDoctor(addDoctor);
    }
}
