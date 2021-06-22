package com.website.demo.specialization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.website.demo.dispensary.Dispensary;
import com.website.demo.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="specialization")
@NoArgsConstructor
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "specializations")
    @JsonBackReference
    private Set<Dispensary> dispensaries;

    public Specialization(String name){
        this.name = name;
    }

}
