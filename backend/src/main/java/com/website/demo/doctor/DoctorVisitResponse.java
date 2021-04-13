package com.website.demo.doctor;

import com.website.demo.schedule.ScheduleDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Data
public class DoctorVisitResponse {

    private DoctorDto doctor;
   // private ScheduleDto schedule;
    private List<LocalTime> hourList;


}
