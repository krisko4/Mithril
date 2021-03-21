package com.website.demo.doctor;

import com.website.demo.patient.PatientDto;
import com.website.demo.schedule.Schedule;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.HOURS;


@RestController
@Data
@RequestMapping("/doctors")

public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{date}")
    public List<Doctor> getDoctors(@PathVariable String date){

        List<DoctorDto> doctorDtoList = doctorService.getAvailableDoctors(date).stream()
                .map(DoctorDto::from)
                .collect(Collectors.toList());
        List<DoctorVisitResponse> dvrList = new ArrayList<>();
        for(DoctorDto doctor : doctorDtoList){
            for(Schedule schedule : doctor.getSchedules()){
                LocalTime startHour = schedule.getStartHour();
                LocalTime endHour = schedule.getEndHour();
                LocalTime ema = startHour;
                List<LocalTime> emaList = new ArrayList<>();
                while(ema.isBefore(endHour)){
                    ema.plusMinutes(20);
                    emaList.add(ema);
                }
                DoctorVisitResponse dvr = new DoctorVisitResponse(doctor, schedule, emaList);
                System.out.println("ema");

            }

        }
        return null;
    }



}
