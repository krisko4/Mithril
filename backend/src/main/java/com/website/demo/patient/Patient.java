package com.website.demo.patient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.website.demo.visit.Visit;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private String first_name;
    private String second_name;
    private String last_name;
    private String pesel;
    private Date birth_date;
    private String phone;
    @OneToMany(mappedBy = "patient")
    private Set<Visit> visitSet;

    public Patient(String first_name, String last_name, String second_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.second_name = second_name;
    }
}
