package com.website.demo.doctor;

import com.website.demo.patient.PatientDto;
import com.website.demo.schedule.Schedule;
import com.website.demo.schedule.ScheduleDto;
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
    public List<DoctorVisitResponse> getDoctors(@PathVariable String date) {
        return doctorService.getAvailableDoctorsByDate(date);
    }



}
