package com.springboot.project.service.impls;

import com.springboot.project.dto.AddNewPatient;
import com.springboot.project.dto.BloodGroupCount;
import com.springboot.project.dto.PatientsDTO;
import com.springboot.project.entity.Patient;
import com.springboot.project.entity.bloodType.BloodGroups;
import com.springboot.project.repository.PatientRepository;
import com.springboot.project.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PatientsDTO> getAllPatients() {
        List<Patient> patientList = patientRepository.findAll();
        List<PatientsDTO> patientsDTOList = new ArrayList<>();
        for (Patient patient : patientList) {
            PatientsDTO patientsDTO = modelMapper.map(patient, PatientsDTO.class);
            patientsDTOList.add(patientsDTO);
        }
        return patientsDTOList;
    }

    @Override
    public PatientsDTO getPatientById(Long id) {
        Patient patientById = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Patient found with this ID : " + id));
        return modelMapper.map(patientById, PatientsDTO.class);
    }

    @Override
    public PatientsDTO findByName(String patientName) {
        Patient patientByName = patientRepository.findByPatientName(patientName);
        return modelMapper.map(patientByName, PatientsDTO.class);
    }

    @Override
    public List<PatientsDTO> findByBloodGroup(BloodGroups group) {
        return patientRepository.findByBloodGroup(group)
                .stream()
                .map(p -> modelMapper.map(p, PatientsDTO.class))
                .toList();
    }

    @Override
    public List<Object[]> countBloodGroup() {
        return patientRepository.countBloodGroupType();
    }


    // Getting the Count of the Blood Group type using the projection ↓
    @Override
    public List<BloodGroupCount> countBloodGroupByProjection() {
        return patientRepository.countBloodGroupByProjection();
    }


    @Override
    public PatientsDTO updatePatientEmailById(Long id, String updateEmail) {
        int rowAffected = patientRepository.updatePatientEmailById(id, updateEmail);
        if (rowAffected == 0) throw new RuntimeException("Patient not found with id : " + id);
        Patient updatedPatient = patientRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id : " + id));
        return modelMapper.map(updatedPatient, PatientsDTO.class);
    }

    @Override
    public Page<Patient> getAllPagedPatients(Pageable pageable) {
        return patientRepository.fetchPagedPatients(pageable);
    }

    @Override
    public PatientsDTO addNewPatient(AddNewPatient addNewPatient) {
        Patient newPatient = new Patient();
        newPatient.setPatientName(addNewPatient.getPatientName());
        newPatient.setGender(addNewPatient.getGender());
        newPatient.setAge(addNewPatient.getAge());
        // checking if the email is coming from the client side or not
        if (addNewPatient.getEmail() == null || addNewPatient.getEmail().isEmpty()) {
            newPatient.setEmail(null);
        } else newPatient.setEmail(addNewPatient.getEmail());
        newPatient.setBloodGroup(addNewPatient.getBloodGroup());

        Patient newPatientSaved = patientRepository.save(newPatient);
        return modelMapper.map(newPatientSaved, PatientsDTO.class);
    }
}
