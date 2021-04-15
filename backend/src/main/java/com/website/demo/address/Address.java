package com.website.demo.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String street;
    private Integer flat_number;
    private String postCode;

    public Address(String country, String city, String street, Integer flat_number, String postCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.flat_number = flat_number;
        this.postCode = postCode;
    }
}

