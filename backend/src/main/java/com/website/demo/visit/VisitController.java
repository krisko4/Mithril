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


    @PostMapping("visits")
    public void addVisit(@RequestBody VisitRequest visitRequest) {
        visitService.addVisit(
                visitRequest.getDate(),
                visitRequest.getDoctorId(),
                visitRequest.getPatientId(),
                visitRequest.getDuration(),
                visitRequest.getInterview(),
                visitRequest.getMedicationIds(),
                visitRequest.getReferrals(),
                visitRequest.getResearch()
                );
    }


    @GetMapping("doctors/{id}/visits")
    public List<VisitDto> getVisitsForDoctorBy(@PathVariable Long id,
                                               @RequestParam(required = false) String date,
                                               @RequestParam(required = false) Long patient_id,
                                               @RequestParam(required = false) Boolean finished) {
        return visitService.getVisitsForDoctorBy(id, date, patient_id, finished).stream().map(VisitDto::from).collect(Collectors.toList());
    }

    @GetMapping("patients/{id}/visits")
    public List<VisitDto> getAllVisitsForPatient(@PathVariable Long id, @RequestParam(required = false) Boolean finished){
        return visitService.getAllVisitsForPatient(id, finished).stream().map(VisitDto::from).collect(Collectors.toList());
    }









}
