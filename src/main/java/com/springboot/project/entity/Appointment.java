package com.springboot.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointment_ID;

    @Column(name = "Timing", nullable = false)
    private LocalDateTime appointment_time;

    @Column(name = "Reason", length = 500)
    private String reason;

    @ManyToOne  // owning side, since appointment doesn't make any sense without patient, so it ows this relationship
    @JoinColumn(name = "patientID", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorID", nullable = false)
    private Doctor doctor;

    // This @ManyToOne() is read as : Many 'Appointment' to 'One' Patient
}
