package com.springboot.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorID")
    private int ID;
    @Column(name = "doctorName", nullable = false)
    private String doctorName;
    @Column(name = "specialization", nullable = false)
    private String specialization;
    @Column(name = "doctorEmailId")
    private String email;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

}
