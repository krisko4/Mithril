package com.website.demo.prescription;

import com.website.demo.visit.Visit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
