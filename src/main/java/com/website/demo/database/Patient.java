package com.website.demo.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

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
