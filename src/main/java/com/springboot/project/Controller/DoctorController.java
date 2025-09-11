package com.springboot.project.Controller;

import com.springboot.project.dto.AddDoctor;
import com.springboot.project.dto.DoctorDTO;
import com.springboot.project.entity.Appointment;
import com.springboot.project.entity.Doctor;
import com.springboot.project.service.AppointmentService;
import com.springboot.project.service.DoctorService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/add/doctor")
    public DoctorDTO addDoctor(@RequestBody AddDoctor addDoctor) {
        return doctorService.addDoctor(addDoctor);
    }

    @PatchMapping(value = "/assign/appointment/new/doctor")
    public Appointment reassignAppointmentToAnotherDoctor(
            @RequestParam int doctorID,
            @RequestParam Long appointmentID) {
        return appointmentService.reassignAppointment(doctorID, appointmentID);
    }
}
