package com.website.demo.patient;

import com.website.demo.visit.Visit;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Data
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public void addPatientList() {
        patientRepository.saveAll(new PatientFactory().getPatientList(10));
    }

    public void addPatient() {
        patientRepository.save(new PatientFactory().getPatient());
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }




    public void removeDoctorForPatient(Long patientId, Long doctorId) {
        patientRepository.removeDoctorForPatient(doctorId, patientId);
    }

    public void addDoctorForPatient(Long doctorId, Long patientId) {
        patientRepository.addDoctorForPatient(doctorId, patientId);
        //return patientRepository.findById(patientID).get();
    }

    public List<PatientDto> getPatientsBy(String character, Long doctorID) {
        if(character != null){
            return patientRepository.findByFirstNameStartsWith(character);
        }
        if(doctorID != null){
            return patientRepository.findByDoctorId(doctorID);
        }
        return patientRepository.findAllPatients();

    }
}
