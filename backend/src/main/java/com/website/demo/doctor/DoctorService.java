package com.website.demo.doctor;

import com.website.demo.schedule.Schedule;
import com.website.demo.schedule.ScheduleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@Data
public class DoctorService {

private final DoctorRepository doctorRepository;


    public List<Doctor> getAvailableDoctors(String dateString) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-d", Locale.US);
        LocalDate localDate = LocalDate.parse(dateString, formatter1);
        return doctorRepository.findAllBySchedules_Date(localDate);
    }

    public List<DoctorVisitResponse> getDoctors(String date) {

        List<Doctor> doctorList = getAvailableDoctors(date);
        List<DoctorVisitResponse> dvrList = new ArrayList<>();
        for(Doctor doctor : doctorList){
            for(Schedule schedule : doctor.getSchedules()){
                LocalTime startHour = schedule.getStartHour();
                LocalTime endHour = schedule.getEndHour();
                List<LocalTime> emaList = new ArrayList<>();
                while(startHour.isBefore(endHour)){
                    emaList.add(startHour);
                    startHour = startHour.plusMinutes(20);
                }
                DoctorVisitResponse dvr = new DoctorVisitResponse(DoctorDto.from(doctor), ScheduleDto.from(schedule), emaList);
                dvrList.add(dvr);
            }

        }
        return dvrList;
    }
}
