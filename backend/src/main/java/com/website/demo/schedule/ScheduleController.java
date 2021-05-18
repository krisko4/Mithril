package com.website.demo.schedule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
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

    @GetMapping("doctors/{id}/schedules")
    public List<Schedule> findSchedulesForDoctorBy(@RequestParam(required = false)String date, @PathVariable Long id){
        return scheduleService.findSchedulesForDoctorBy(date, id);
    }

    @PostMapping("doctors/{id}/schedules")
    public void setSchedule(@RequestBody ScheduleRequest scheduleRequest, @PathVariable Long id){
        scheduleService.setSchedule(
                scheduleRequest.getDate(),
                scheduleRequest.getStartHour(),
                scheduleRequest.getEndHour(),
                scheduleRequest.getBreakDuration(),
                scheduleRequest.getVisitDuration(),
                id
                );
    }

    @DeleteMapping("doctors/{id}/schedules")
    public void deleteSchedulesForDoctorBy(@RequestParam String date, @PathVariable Long id){
        scheduleService.deleteSchedulesForDoctorBy(date, id);
    }




}
