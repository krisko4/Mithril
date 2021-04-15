package com.website.demo.specialization;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="specialization")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


}
