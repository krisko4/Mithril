package com.website.demo.schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.website.demo.user.AppUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Entity(name = "schedule")
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime startHour;
    private LocalTime endHour;
    @ManyToMany(mappedBy = "schedules")
    @JsonBackReference
    private Set<AppUser> doctors;
}
