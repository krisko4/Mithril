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
        System.out.println("ema");
    }

    public Set<Visit> getVisit(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("ema"));
        return patient.getVisitSet();
    }
}
