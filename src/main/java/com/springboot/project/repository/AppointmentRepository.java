package com.springboot.project.repository;

import com.springboot.project.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This repository is being created using the JPA Buddy plugin, which has created this repository automatically
 * */

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {



}