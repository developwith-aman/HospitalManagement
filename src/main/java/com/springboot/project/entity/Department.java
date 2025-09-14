package com.springboot.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departmentID", unique = true, nullable = false)
    private Long departmentID;

    @Column(name = "departmentCode")
    private String departmentCode;

    @Column(name = "departmentName", nullable = false, unique = true, length = 200)
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "departmentHead", nullable = false, unique = true)
    @JsonBackReference("doctor-department")
    private Doctor headDoctor;

    @ManyToMany   // Many departments can have many doctors
    @JoinTable(
            name = "doctor_department",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;
}
