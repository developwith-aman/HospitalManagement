package com.springboot.project.dto;

import com.springboot.project.entity.bloodType.BloodGroups;

public class AddNewPatient {

    private String patientName;
    private String gender;
    private int age;
    private String email;
    private BloodGroups bloodGroup;

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
}
