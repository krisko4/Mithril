package com.website.demo.visit;

import com.website.demo.patient.Patient;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@Data
public class VisitController {

    private final VisitService visitService;


    @PostMapping("add")
    public void addVisit(@RequestBody VisitRequest visitRequest) {
        visitService.addVisit(visitRequest);
    }


    @GetMapping("doctors/{id}/visits")
    public List<VisitDto> getVisitsForDoctorBy(@PathVariable Long id, @RequestParam(required = false) String date, @RequestParam(required = false) Long patient_id) {
        return visitService.getVisitsForDoctorBy(id, date, patient_id).stream().map(VisitDto::from).collect(Collectors.toList());
    }

    @GetMapping("patients/{id}/visits")
    public List<VisitDto> getAllVisitsForPatient(@PathVariable Long id){
        return visitService.getAllVisitsForPatient(id).stream().map(VisitDto::from).collect(Collectors.toList());
    }


    @GetMapping("get/hours")
    public List<LocalTime> getAvailableVisitHoursForDoctorByDate(@RequestParam String date, @RequestParam Long doctor_id) {
        return visitService.getAvailableVisitHoursForDoctorByDate(date, doctor_id);
    }

    @GetMapping("/get/all")
    public List<VisitDto> getAllVisitsForDoctorByDate(@RequestParam String date, @RequestParam Long doctor_id){
        return visitService.getAllVisitsForDoctorByDate(date, doctor_id).stream().map(VisitDto::from).collect(Collectors.toList());
    }


    @GetMapping("forPatientAndDoctor")
    public List<VisitDto> getAllVisitsForPatient(@RequestParam Long patientID, @RequestParam Long doctorID){
        return visitService.getAllVisitsForPatientAndDoctor(patientID, doctorID).stream().map(VisitDto::from).collect(Collectors.toList());
    }





}
