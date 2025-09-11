package com.springboot.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "insurance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {

    // Defining column mappings and constraints using JPA annotations
    @Id
    @Column(name = "insuranceID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyID;

    @Column(name = "policyNumber", nullable = false, unique = true)
    private String policyNumber;

    @Column(name = "provider", nullable = false)
    private String policyProvider;

    @Column(name = "effectiveDate", nullable = false)
//    @CreationTimestamp
    private LocalDateTime effectiveDate;

    @Column(name = "expiryDate", nullable = false)
    private LocalDate expiryDate;

    @OneToOne(mappedBy = "insurance")  // inverse side
    @JsonIgnore
    @JoinColumn(name = "patient")
    private Patient patient;
}