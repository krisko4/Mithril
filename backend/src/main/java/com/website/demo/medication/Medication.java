package com.website.demo.medication;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.website.demo.prescription.Prescription;
import com.website.demo.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int onPrescription;
    private String description;
    @ManyToMany(mappedBy = "medications")
    @JsonBackReference
    private Set<Prescription> prescriptions;

}
