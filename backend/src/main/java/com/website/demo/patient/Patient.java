package com.website.demo.patient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.website.demo.address.Address;
import com.website.demo.visit.Visit;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;
    private String phone;
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "patient")
    private Set<Visit> visitSet;

    public Patient(String firstName, String lastName, String secondName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
    }
}
