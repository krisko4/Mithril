package com.website.demo.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class ScheduleDto {
    private Long id;
    private LocalDate date;
    private LocalTime startHour;
    private LocalTime endHour;

    public static ScheduleDto from(Schedule schedule){
        ScheduleDto dto = new ScheduleDto();
        dto.id = schedule.getId();
        dto.date = schedule.getDate();
        dto.startHour = schedule.getStartHour();
        dto.endHour = schedule.getEndHour();

        return dto;
    }
}
