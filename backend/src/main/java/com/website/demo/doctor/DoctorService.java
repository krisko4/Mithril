package com.website.demo.doctor;

import com.website.demo.schedule.Schedule;
import com.website.demo.schedule.ScheduleDto;
import com.website.demo.schedule.ScheduleService;
import com.website.demo.validation.EmailValidator;
import com.website.demo.validation.PasswordValidator;
import com.website.demo.visit.Visit;
import com.website.demo.visit.VisitService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;

@Service
@AllArgsConstructor
@Data
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final VisitService visitService;
    private final ScheduleService scheduleService;




    public List<DoctorDto> getAvailableDoctorsByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d", Locale.US);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return doctorRepository.findAllBySchedules_Date(localDate);
    }

    public void validateFirstRegistrationStep(DoctorRequest doctorRequest) {

        if(doctorRepository.findByEmail(doctorRequest.getEmail()) != null){
            throw new RuntimeException("E-mail already exists.");
        }
        if(doctorRepository.findByUsername(doctorRequest.getUsername()) != null){
            throw new RuntimeException("Username already exists.");
        }

        PasswordValidator passwordValidator = new PasswordValidator();
        passwordValidator.validate(doctorRequest.getPassword());
        passwordValidator.checkIfEqual(doctorRequest.getPassword(), doctorRequest.getConfirmPassword());
        EmailValidator emailValidator = new EmailValidator();
        emailValidator.validate(doctorRequest.getEmail());
        emailValidator.checkIfEqual(doctorRequest.getEmail(), doctorRequest.getConfirmEmail());





      //  doctorRequest.setPassword(new BCryptPasswordEncoder().encode(doctorRequest.getPassword()));
      //  doctorRepository.save(new Doctor(doctorRequest));

    }



 /*   public List<LocalTime> getVisitsForDoctor(Doctor doctor){
        List<DoctorVisitResponse> dvrList = new ArrayList<>();
        // lista wizyt w danym dniu dla danego doktora
        List<Visit> visitList = visitService.getVisitsByDate(date, doctor.getId());
        // lista godzin w jakich odbywają się wizyty
        List<LocalTime> visitHourList = new ArrayList<>();
        for (Visit visit : visitList) {
            int hour = visit.getDate().getHour();
            int minute = visit.getDate().getMinute();
            LocalTime visitHour = LocalTime.of(hour, minute);
            visitHourList.add(visitHour);
        }
        // sprawdzamy dni pracy doktora - jeśli data jest inna niż ta, którą wybraliśmy, szukamy dalej
        for (Schedule schedule : doctor.getSchedules()) {
            if (schedule.getDate().compareTo(localDate) != 0) {
                continue;
            }
            LocalTime startHour = schedule.getStartHour();
            LocalTime endHour = schedule.getEndHour();
            List<LocalTime> hourList = new ArrayList<>();
            while (startHour.isBefore(endHour)) {
                if (!visitHourList.contains(startHour)){
                    hourList.add(startHour);
                }
                startHour = startHour.plusMinutes(20);
            }
            DoctorVisitResponse dvr = new DoctorVisitResponse(DoctorDto.from(doctor), hourList);
            dvrList.add(dvr);
        }
    }*/

}
