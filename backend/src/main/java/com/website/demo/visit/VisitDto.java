package com.website.demo.visit;

import com.website.demo.patient.Patient;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
@Data
public class VisitDto {

    private LocalDate date;
    private String description;

    public VisitDto(Visit visit){
        date = visit.getDate();
        description = visit.getDescription();
    }




}
