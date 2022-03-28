package com.website.demo.visit.request;

import lombok.Data;

@Data
public class NewUnfinishedVisitRequest {

    private Long patientId;
    private Long doctorId;
    private String date;
    private int duration;

}
