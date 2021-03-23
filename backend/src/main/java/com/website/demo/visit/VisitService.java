package com.website.demo.visit;

import com.website.demo.doctor.Doctor;
import com.website.demo.patient.Patient;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Data
public class VisitService {

    private final VisitRepository visitRepository;

    public void addVisit(Patient patient, Doctor doctor, LocalDateTime date) {
        Visit visit = new Visit(patient, doctor, date);
        visitRepository.save(visit);
    }


    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public List<Visit> getVisitsByDate(String date, Long id) {
        return visitRepository.findAllVisitsForOneDoctorByDate(date, id);
    }

 //   public List<Visit> getVisitsBy(VisitRequest visitRequest) {
   //     List<Visit> visitList;
  //      if (visitRequest.getDate() != null) {
      //      visitList = getVisitsByDate(visitRequest.getDate());
      //      return visitList;
    //    }
  //      visitList = visitRepository.findAll();
  //      return visitList;
  //  }
}
