package com.website.demo.visit;

import com.website.demo.doctor.Doctor;
import com.website.demo.patient.Patient;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Data
public class VisitService {

    private final VisitRepository visitRepository;

    public void addVisit(Patient patient, Doctor doctor, LocalDate date) {
        Visit visit = new Visit(patient, doctor, date);
        visitRepository.save(visit);
    }



    public List<Visit> getVisits() {
       return visitRepository.findAll();
    }
}
