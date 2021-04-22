package com.website.demo.visit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VisitResponse {

    public String date;
    public String patientName;
    public String description;

}
