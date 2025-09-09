package com.springboot.project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.project.entity.bloodType.BloodGroups;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patientID")
    private Long patientID;
    @Column(name = "patientName")
    private String patientName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "patientAge")
    private int age;
    @Column(name = "email")
    private String email;
    @Column(name = "bloodGroup")
    // This column is created by hibernate, it is not already been created inside the database
    @Enumerated(EnumType.STRING) // Using the Enums here with the type STRING, mostly the ORDINAL is used in industries
    private BloodGroups bloodGroup;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "insuranceID")  // Owning side of relationship
    private Insurance insurance;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @JsonManagedReference
    private List<Appointment> appointments;   // Since a patient can have multiple appointments

    @Column(name = "appointmentTime", updatable = false)
    @CreationTimestamp
    private LocalDateTime appointmentTime;

    public Long getPatientID() {
        return patientID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BloodGroups getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroups bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    // We are not writing the Getters and Setters here, since we are going to use the Lombok
    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }


    // This ↓↓ is a derived (computed) boolean getter that Jackson treats as a virtual JSON property.
    // It’s not persisted in the database, because there’s no field.

    /**There is no hasInsurance field in your entity.
    Instead, you’re computing the value dynamically:
    If insurance is set → return true.
    If insurance is null → return false.
     */
    @JsonProperty("insured")
    public boolean getHasInsurance() {
        return this.insurance != null;
    }
}
