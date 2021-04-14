package com.website.demo.patient;

import com.website.demo.visit.Visit;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @CrossOrigin
    @GetMapping
    public List<PatientDto> getPatients(){
        return patientService.getPatients().stream()
                .map(PatientDto::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/visit/{id}" )
    public Set<Visit> getVisitsForOnePatient(@PathVariable Long id){
        return patientService.getVisit(id);
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatients(@PathVariable Long id){
        return patientService.findPatientById(id);
    }

    @CrossOrigin
    @GetMapping("/bychar")
    public List<PatientDto> getPatientsByChar(@RequestParam String character){
        return patientService.getPatientsByChar(character);
    }


    @PostMapping
    public void addPatientList(){
        patientService.addPatientList();
    }

    @PostMapping("/add")
    public void addPatient(){
        patientService.addPatient();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        patientService.deleteById(id);
    }

    @GetMapping("all")
    public List<Patient> getPatients1(){
        return patientService.getPatients();
    }



}
