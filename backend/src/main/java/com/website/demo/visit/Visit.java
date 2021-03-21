package com.website.demo.visit;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.website.demo.doctor.Doctor;
import com.website.demo.patient.Patient;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "visit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Visit {


    public Visit(Patient patient, Doctor doctor, LocalDate date){
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "patient_id")
    private Patient patient;



}
