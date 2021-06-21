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

    @GetMapping("doctors/{id}/visit-hours")
    public List<LocalTime> getAvailableVisitHoursByDate(@PathVariable Long id,
                                                        @RequestParam String date){
        return visitService.getAvailableVisitHoursForDoctorByDate(date, id);
    }

    @GetMapping("doctors/{id}/visits")
    @ResponseBody
    public List<VisitResponse> getVisitsForDoctorBy(@PathVariable Long id,
                                               @RequestParam(required = false) String date,
                                               @RequestParam(required = false) Long patient_id,
                                               @RequestParam(required = false) Boolean finished) {
        return visitService.getFullVisitDataForDoctorBy(id, date, patient_id, finished);
    }

    @GetMapping("patients/{id}/visits")
    public List<VisitResponse> getAllVisitsForPatient(@PathVariable Long id,
                                                      @RequestParam(required = false) Boolean finished){
        return visitService.getFullVisitDataForPatient(id, finished);
    }






}
