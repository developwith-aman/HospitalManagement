package com.springboot.project.repository;

import com.springboot.project.dto.patient.PatientAppointmentsDTO;
import com.springboot.project.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This repository is being created using the JPA Buddy plugin, which has created this repository automatically
 * */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT new com.springboot.project.dto.patient.PatientAppointmentsDTO(p.numberOfAppointments, d.ID, a.reason)" +
            "FROM Appointment a " +
            "JOIN a.patient p " +
            "JOIN a.doctor d " +
            "WHERE p.patientID = :patientId")
    List<PatientAppointmentsDTO> fetchPatientAppointments(@Param("patientId") Long patientId);}