package com.springboot.project.entity;

import com.springboot.project.entity.staff.StaffDepartments;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long staffID;

    @Column(name = "Name")
    private String staffMemberName;

    @Column(name = "Age")
    private int age;

    @Column(name = "Salary")
    private double salary;

    @Column(name = "Staff_Department")
    @Enumerated(EnumType.STRING)
    private StaffDepartments staffDepartment;

}
