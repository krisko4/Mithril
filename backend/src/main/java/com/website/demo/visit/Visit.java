package com.website.demo.visit;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.website.demo.doctor.Doctor;
import com.website.demo.patient.Patient;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "visit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Visit {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
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
