package com.website.demo.visit;

import com.website.demo.patient.Patient;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@Data
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;

    @CrossOrigin
    @PostMapping("/add")
    public void addVisit(@RequestBody VisitRequest visitRequest) {
        visitService.addVisit(visitRequest);
    }

    @CrossOrigin
    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }

    @CrossOrigin
    @GetMapping("/get")
    public List<LocalTime> getVisitsForDoctorByDate(@RequestParam String date, @RequestParam Long doctor_id) {
        return visitService.getVisitsForDoctorByDate(date, doctor_id);
    }

//    @GetMapping("/bydate")
//    public List<Visit> getVisitsBy(VisitRequest visitRequest){
    //       return visitService.getVisitsBy(visitRequest);
    //   }

}
