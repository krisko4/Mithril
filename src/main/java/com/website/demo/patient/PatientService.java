package com.website.demo.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> findPatientById(Long id){
        return patientRepository.findById(id);
    }

    public void addPatientList(){
        patientRepository.saveAll(new PatientFactory().getPatientList(10));
    }

    public void addPatient(Long id) {
        patientRepository.save(new PatientFactory().getPatient(id));
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
        System.out.println("ema");
    }
}
