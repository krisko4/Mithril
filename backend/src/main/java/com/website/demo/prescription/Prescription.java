package com.website.demo.prescription;

import com.website.demo.medication.Medication;
import com.website.demo.schedule.Schedule;
import com.website.demo.visit.Visit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "prescription")
@Getter
@Setter
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private LocalDateTime creationDate;
    private LocalDate expirationDate;
    private String description;
    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
    @ManyToMany
    @JoinTable(name = "prescription_medication", joinColumns = {@JoinColumn(name = "prescription_id")}, inverseJoinColumns = {@JoinColumn(name = "medication_id")})
    private Set<Medication> medications;
}
