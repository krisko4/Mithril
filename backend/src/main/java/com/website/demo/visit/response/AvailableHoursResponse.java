package com.website.demo.visit.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
public class AvailableHoursResponse {

    private List<LocalTime> hourList;
    private int visitDuration;


}
