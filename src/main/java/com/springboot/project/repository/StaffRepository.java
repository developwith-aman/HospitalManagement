package com.springboot.project.repository;

import com.springboot.project.dto.StaffDTO;
import com.springboot.project.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{
}
