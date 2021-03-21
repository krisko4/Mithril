package com.website.demo.schedule;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

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
}
