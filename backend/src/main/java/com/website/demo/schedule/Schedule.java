package com.website.demo.schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.website.demo.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Entity(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String startHour;
    private String endHour;
    @ManyToMany(mappedBy = "schedules")
    @JsonBackReference
    private Set<AppUser> doctors;
    private int breakDuration;
    private int visitDuration;


    public Schedule(LocalDate date, String startHour, String endHour, int breakDuration, int visitDuration) {
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.breakDuration = breakDuration;
        this.visitDuration = visitDuration;
    }
}
