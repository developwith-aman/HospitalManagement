package com.springboot.project.dto;


import com.springboot.project.entity.bloodType.BloodGroups;

public class PatientsDTO {

    private Long patientID;
    private String patientName;
    private String gender;
    private int age;
    private String email;
    private BloodGroups bloodGroup;
    private Boolean hasInsurance = false;

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

    public Boolean getHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(Boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public PatientsDTO() {
    }

    public PatientsDTO(Long patientID, String patientName, String gender, int age, String email, BloodGroups bloodGroup, Boolean hasInsurance) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.bloodGroup = bloodGroup;
        this.hasInsurance = hasInsurance;
    }
}


