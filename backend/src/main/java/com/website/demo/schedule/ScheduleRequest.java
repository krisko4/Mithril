package com.website.demo.schedule;


import lombok.Data;

@Data
public class ScheduleRequest {

    private String date;
    private String startHour;
    private String endHour;
    private int visitDuration;
    private int breakDuration;

}
