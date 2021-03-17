package com.website.demo.database;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<Patient> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatients(@PathVariable Long id){
        return patientService.findPatientById(id);
    }


    @PostMapping
    public void addPatientList(){
        patientService.addPatientList();
    }

    @PostMapping("/{id}")
    public void addPatient(@PathVariable Long id){
        patientService.addPatient(id);
    }

}
