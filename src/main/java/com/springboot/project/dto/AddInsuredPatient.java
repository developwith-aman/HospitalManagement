package com.springboot.project.dto;

import com.springboot.project.entity.Insurance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddInsuredPatient {
    private AddNewPatient patient;
    private Insurance insurance;
}
