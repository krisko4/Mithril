package com.website.demo.patient;

import com.website.demo.visit.Visit;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("patients")
public class PatientController {

    private final PatientService patientService;


    @GetMapping("{id}")
    public Optional<Patient> getPatient(@PathVariable Long id) {
        return patientService.findPatientById(id);
    }


    @GetMapping
    public List<PatientDto> getPatientsBy(@RequestParam(required = false) String name, @RequestParam(required = false) Long doctor_id) {
        return patientService.getPatientsBy(name, doctor_id);
    }


    @PatchMapping("{id}/remove-doctor")
    public void removeDoctorForPatient(@PathVariable Long id, @RequestParam Long doctorId) {
        patientService.removeDoctorForPatient(id, doctorId);
    }

    @PatchMapping("{id}/add-doctor")
    public void addDoctorForPatient(@PathVariable Long id, @RequestParam Long doctorId) {
        patientService.addDoctorForPatient(doctorId, id);
    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        patientService.deleteById(id);
    }




}
