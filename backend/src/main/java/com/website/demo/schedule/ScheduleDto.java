package com.website.demo.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class ScheduleDto {
    private Long id;
    private Date date;
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
