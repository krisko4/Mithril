package com.website.demo.visit;

import com.website.demo.doctor.Doctor;
import com.website.demo.patient.Patient;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@Data
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;


    @PostMapping("/add")
    public void addVisit(@RequestBody Patient patient, Doctor doctor, LocalDate date){
        visitService.addVisit(patient, doctor, date);
    }

    @GetMapping
    public List<Visit> getVisits(){
        return visitService.getVisits();
    }

}
