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
@RequestMapping("visits")
public class VisitController {

    private final VisitService visitService;


    @PostMapping("add")
    public void addVisit(@RequestBody VisitRequest visitRequest) {
        visitService.addVisit(visitRequest);
    }


    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }


    @GetMapping("get/hours")
    public List<LocalTime> getAvailableVisitHoursForDoctorByDate(@RequestParam String date, @RequestParam Long doctor_id) {
        return visitService.getAvailableVisitHoursForDoctorByDate(date, doctor_id);
    }

    @GetMapping("/get/all")
    public List<VisitDto> getAllVisitsForDoctorByDate(@RequestParam String date, @RequestParam Long doctor_id){
        return visitService.getAllVisitsForDoctorByDate(date, doctor_id);
    }

    @GetMapping("forPatient")
    public List<VisitDto> getAllVisitsForPatient(@RequestParam Long patientID){
        return visitService.getAllVisitsForPatient(patientID).stream().map(VisitDto::from).collect(Collectors.toList());
    }

    @GetMapping("forPatientAndDoctor")
    public List<VisitDto> getAllVisitsForPatient(@RequestParam Long patientID, @RequestParam Long doctorID){
        return visitService.getAllVisitsForPatientAndDoctor(patientID, doctorID).stream().map(VisitDto::from).collect(Collectors.toList());
    }



//    @GetMapping("/bydate")
//    public List<Visit> getVisitsBy(VisitRequest visitRequest){
    //       return visitService.getVisitsBy(visitRequest);
    //   }

}
