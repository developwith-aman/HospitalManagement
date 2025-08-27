package com.springboot.project.entity;

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
    @Column(name = "departmentCode")
    private Long code;

    @Column(name = "departmentName", nullable = false, unique = true, length = 200)
    private String name;

    @OneToOne
    @JoinColumn(name = "departmentHead", nullable = false, unique = true)
    private Doctor headDoctor;

    @ManyToMany   // Many departments can have many doctors
    @JoinTable(
            name = "doctor_department",
            joinColumns = @JoinColumn(name = "department_code"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;
}
