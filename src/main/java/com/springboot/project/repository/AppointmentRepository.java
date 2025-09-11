package com.springboot.project.repository;

import com.springboot.project.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository is being created using the JPA Buddy plugin, which has created this repository automatically
 * */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}