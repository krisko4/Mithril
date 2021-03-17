package com.website.demo.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "data")
@NoArgsConstructor
@AllArgsConstructor

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String secondname;


    public Patient(String name, String surname, String secondname) {
        this.name = name;
        this.surname = surname;
        this.secondname = secondname;
    }


/**
    private String name;
    private String surname;
    private String secondname;
    @Id
    private int patient_ID;
    private int pesel;
   // @DateTimeFormat
    //@Column(name="DateOfBirth")
    private String dateofbirth;
    private String streetname;
    private int streetnumber;

    private int housenumber;
    private String town;
    private String postcode;
    **/


}
