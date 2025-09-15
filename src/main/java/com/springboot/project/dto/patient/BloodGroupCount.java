package com.springboot.project.dto.patient;

import com.springboot.project.entity.bloodType.BloodGroups;

public class BloodGroupCount {
    private BloodGroups bloodGroups;
    private Long Count;

    public BloodGroups getBloodGroups() {
        return bloodGroups;
    }

    public void setBloodGroups(BloodGroups bloodGroups) {
        this.bloodGroups = bloodGroups;
    }

    public Long getCount() {
        return Count;
    }

    public void setCount(Long count) {
        Count = count;
    }

    public BloodGroupCount(BloodGroups bloodGroups, Long count) {
        this.bloodGroups = bloodGroups;
        Count = count;
    }

    public BloodGroupCount() {
    }
}
