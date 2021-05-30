package com.website.demo.specialization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.website.demo.dispensary.Dispensary;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="specialization")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "specializations")
    @JsonBackReference
    private Set<Dispensary> dispensaries;
}
