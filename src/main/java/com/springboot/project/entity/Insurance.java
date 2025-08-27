package com.springboot.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyID;

    @Column(nullable = false, unique = true, name = "policyNumber")
    private String policyNumber;

    @Column(nullable = false, name = "provider")
    private String policyProvider;

    @Column(nullable = false, name = "effectiveDate")
    @CreationTimestamp
    private LocalDateTime effectiveDate;

    @Column(nullable = false, name = "expiryDate")
    private LocalDate expiryDate;

    @OneToOne(mappedBy = "insurance")  // inverse side
    @JsonIgnore
    private Patient patient;
}