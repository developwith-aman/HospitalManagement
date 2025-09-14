package com.springboot.project.Controller;

import com.springboot.project.dto.AddDoctor;
import com.springboot.project.dto.DoctorDTO;
import com.springboot.project.dto.PatientsDTO;
import com.springboot.project.dto.UpdateEmail;
import com.springboot.project.entity.Appointment;
import com.springboot.project.entity.Doctor;
import com.springboot.project.service.AppointmentService;
import com.springboot.project.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    public DoctorController(DoctorService doctorService,
                            AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    // URL : http://localhost:8080/doctors/.....
    @PostMapping(value = "/add/doctor")
    public DoctorDTO addDoctor(@RequestBody AddDoctor addDoctor) {
        return doctorService.addDoctor(addDoctor);
    }

    @PatchMapping(value = "/update/email/{doctorID}")
    public DoctorDTO updateDoctorEmail(@PathVariable int doctorID, @RequestBody UpdateEmail updateEmail) {
        return doctorService.updateDoctorEmailById(doctorID, updateEmail.getEmail());
    }


    // Reassigning appointment to a new doctor
    // {{baseURL}}/doctors/assign/appointment/new/doctor?doctorID=1&appointmentID=5
    @PatchMapping(value = "/assign/appointment/new/doctor")
    public Appointment reassignAppointmentToAnotherDoctor(
            @RequestParam int doctorID,
            @RequestParam Long appointmentID) {
        return appointmentService.reassignAppointment(doctorID, appointmentID);
    }

    @GetMapping(value = "/get/appointments/{doctorID}")
    public List<Appointment> getAppointmentsOfDoctor(@PathVariable int doctorID){
        return appointmentService.getAppointmentsOfDoctor(doctorID);
    }
}
