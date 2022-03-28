package com.website.demo.patient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.website.demo.address.Address;
import com.website.demo.user.AppUser;
import com.website.demo.visit.Visit;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "patient")
@Entity(name = "Patient")
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
    private LocalDate birthdate;
    private String phone;
    private String email;
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<Visit> visitSet;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private AppUser appUser;

    public Patient(String firstName, String lastName, String secondName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
    }

    public Patient(String firstName,
                   String secondName,
                   String lastName,
                   String pesel,
                   LocalDate birthdate,
                   String phone,
                   String email,
                   Address address,
                   AppUser appUser
    ) {

        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.birthdate = birthdate;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.appUser = appUser;

    }

}
