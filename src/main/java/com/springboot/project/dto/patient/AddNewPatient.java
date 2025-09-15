package com.springboot.project.dto.patient;

import com.springboot.project.entity.Insurance;
import com.springboot.project.entity.bloodType.BloodGroups;
import org.aspectj.weaver.reflect.InternalUseOnlyPointcutParser;

import java.time.LocalDateTime;

public class AddNewPatient {

    private String patientName;
    private String gender;
    private int age;
    private String email;
    private BloodGroups bloodGroup;
    private LocalDateTime arrivalTime;
    private Insurance insurance;
    private Boolean hasInsurance = false;

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

    public Boolean getHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(Boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
