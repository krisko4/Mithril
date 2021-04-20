package com.website.demo.visit;

//import com.website.demo.doctor.DoctorRepository;
import com.website.demo.patient.Patient;
import com.website.demo.patient.PatientRepository;
import com.website.demo.schedule.Schedule;
import com.website.demo.schedule.ScheduleService;
import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Data
public class VisitService {

    private final VisitRepository visitRepository;
    private final ScheduleService scheduleService;
//    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppUserRepository appUserRepository;

    public void addVisit(VisitRequest visitRequest) {
        Patient patient = patientRepository.findById(visitRequest.getPatient_id()).get();
        AppUser doctor = appUserRepository.findById(visitRequest.getDoctor_id()).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(visitRequest.getDate(), formatter);
        Visit visit = new Visit(patient, doctor, localDateTime);
        visitRepository.save(visit);
    }


    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public List<Visit> getVisitsByDate(String date, Long id) {
        return visitRepository.findAllVisitsForOneDoctorByDate(date, id);
    }

    public List<LocalTime> getVisitsForDoctorByDate(String date, Long doctor_id){
        Schedule schedule = scheduleService.findScheduleForDoctorByDate(date, doctor_id);
        LocalTime startHour = schedule.getStartHour();
        LocalTime endHour = schedule.getEndHour();
        List<Visit> visitList = getVisitsByDate(date, doctor_id);
        List<LocalTime> visitHourList = new ArrayList<>();
        if(!visitList.isEmpty()) {
            for (Visit visit : visitList) {
                int hour = visit.getDate().getHour();
                int minute = visit.getDate().getMinute();
                LocalTime visitHour = LocalTime.of(hour, minute);
                visitHourList.add(visitHour);
            }
        }
        List<LocalTime> hourList = new ArrayList<>();
        while (startHour.isBefore(endHour)) {
            if (!visitHourList.contains(startHour)){
                hourList.add(startHour);
            }
            startHour = startHour.plusMinutes(20);
        }
        return hourList;
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
