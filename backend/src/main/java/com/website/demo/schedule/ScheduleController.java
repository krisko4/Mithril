package com.website.demo.schedule;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public List<ScheduleDto> getAll(){
        return scheduleService.getAll()
                .stream()
                .map(ScheduleDto::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/schedule")
    public Schedule findScheduleForDoctorByDate(@RequestParam String date, @RequestParam Long doctor_id){
        return scheduleService.findScheduleForDoctorByDate(date, doctor_id);
    }


}
