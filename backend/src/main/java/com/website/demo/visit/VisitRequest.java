package com.website.demo.visit;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.website.demo.doctor.Doctor;
import com.website.demo.patient.Patient;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VisitRequest {

    private Long patient_id;
    private Long doctor_id;
    private String date;

}
