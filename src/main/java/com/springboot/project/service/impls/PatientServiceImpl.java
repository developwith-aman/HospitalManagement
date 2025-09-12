package com.springboot.project.service.impls;

import com.springboot.project.dto.*;
import com.springboot.project.entity.Insurance;
import com.springboot.project.entity.Patient;
import com.springboot.project.entity.bloodType.BloodGroups;
import com.springboot.project.repository.AppointmentRepository;
import com.springboot.project.repository.DoctorRepository;
import com.springboot.project.repository.InsuranceRepository;
import com.springboot.project.repository.PatientRepository;
import com.springboot.project.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final AppointmentRepository appointmentRepository;
    private final InsuranceRepository insuranceRepository;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper,
                              AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              InsuranceRepository insuranceRepository) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.appointmentRepository = appointmentRepository;
        this.insuranceRepository = insuranceRepository;
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
        Patient patientById = patientRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("No Patient found with this ID : " + id));
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

    public PatientsDTO dischargePatientFromHospital(Long patientID) {
        Patient patient = patientRepository
                .findById(patientID)
                .orElseThrow(() -> new IllegalArgumentException("No patient found with this id..."));

        /** Delete all appointments of the patient before deleting the patient itself.
         * Appointments are the owning side of the relationship, so we must remove them first to avoid
         * foreign key constraint violations. Unlike Insurance (OneToOne with orphanRemoval),
         * clearing the appointments list alone doesn't delete them automatically when using JPQL.
         * Bulk JPQL deletes bypass Hibernate's cascade/orphan rules, so we explicitly delete appointments first.
         **/
        if (patient.getAppointments() != null) {
            patientRepository.deleteAppointmentsByPatientId(patientID);
        }
        boolean hadInsurance = patient.getInsurance() != null;
        patient.setInsurance(null);//Works here because orphanRemoval=true will mark the insurance as orphan and delete it automatically

       /**When deleting a patient, we set insurance to null to trigger orphanRemoval and delete it from the DB.
        * However, mapping the patient to DTO afterward would show hasInsurance = false, because the entity was modified.
        * Two ways to handle this:
        * Skip mapping hasInsurance using ModelMapper:
        * modelMapper.typeMap(Patient.class, PatientsDTO.class).addMappings(mapper -> mapper.skip(PatientsDTO::setHasInsurance));
        * Or, store the original value before nullifying and restore it in the DTO:
        * boolean hadInsurance = patient.getInsurance() != null;
        * patient.setInsurance(null);
        * dto.setHasInsurance(hadInsurance);
        **/

        int rowAffectedAfterDeletion = patientRepository.dischargePatient(patientID);
        if (rowAffectedAfterDeletion == 0) throw new IllegalArgumentException("Cannot delete NULL patient");
        PatientsDTO patientsDTO = modelMapper.map(patient, PatientsDTO.class, "Patient Deleted");
        patientsDTO.setHasInsurance(hadInsurance);
        return patientsDTO;
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
        if (rowAffected == 0) throw new IllegalArgumentException("Patient not found with id : " + id);
        Patient updatedPatient = patientRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id : " + id));
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
        newPatient.setArrivalTime(addNewPatient.getArrivalTime());

        if (addNewPatient.getInsurance() != null) {
            Insurance insurance = new Insurance();
            insurance.setPolicyNumber(addNewPatient.getInsurance().getPolicyNumber());
            insurance.setPolicyProvider(addNewPatient.getInsurance().getPolicyProvider());
            insurance.setEffectiveDate(addNewPatient.getInsurance().getEffectiveDate());
            insurance.setExpiryDate(addNewPatient.getInsurance().getExpiryDate());

            insurance.setPatient(newPatient);
            newPatient.setInsurance(insurance);

        }
        Patient newPatientSaved = patientRepository.save(newPatient);
        PatientsDTO patientsDTO = modelMapper.map(newPatientSaved, PatientsDTO.class);
        patientsDTO.setHasInsurance(newPatient.getInsurance() != null);
        return patientsDTO;
    }


    // this takes patients and insurance too, but if the insurance is not provided, it will only save the patient
    @Override
    public PatientsDTO addInsuredPatient(AddNewPatient addNewPatient, Insurance insurance) {
        Patient newPatient = new Patient();
        newPatient.setPatientName(addNewPatient.getPatientName());
        newPatient.setGender(addNewPatient.getGender());
        newPatient.setAge(addNewPatient.getAge());
        if (addNewPatient.getEmail() == null || addNewPatient.getEmail().isEmpty()) {
            newPatient.setEmail(null);
        } else newPatient.setEmail(addNewPatient.getEmail());
        newPatient.setBloodGroup(addNewPatient.getBloodGroup());

        if (insurance != null) {   // it decides whether the insurance and patient will be linked or not...
            insurance.setPatient(newPatient);
            newPatient.setInsurance(insurance);
        }
        Patient newSavedPatient = patientRepository.save(newPatient);
        PatientsDTO patientsDTO = modelMapper.map(newSavedPatient, PatientsDTO.class);
        patientsDTO.setHasInsurance(newSavedPatient.getInsurance() != null);
        return patientsDTO;
    }


    // Fetching Appointments of a patient
    @Override
    public List<PatientAppointmentsDTO> getPatientAppointments(Long patientId) {
        return appointmentRepository.fetchPatientAppointments(patientId);
    }
}
