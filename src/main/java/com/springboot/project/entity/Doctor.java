package com.springboot.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.List;

@Entity
@Table(name = "doctor")
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorID")
    @JsonProperty("id")
    private int ID;

    @Column(name = "doctorName", nullable = false)
    private String doctorName;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @Column(name = "doctorEmailId", unique = true, length = 150)
    private String email;

    @OneToMany(mappedBy = "doctor")   // inverse side
    @JsonManagedReference
    private List<Appointment> appointments;

    @ManyToMany(mappedBy = "doctors")
    @JsonIgnore
    private List<Department> departments;

    @OneToOne(mappedBy = "headDoctor")
    @JsonManagedReference("doctor-department")
    private Department headOfDepartment;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Department getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Department headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }
}
