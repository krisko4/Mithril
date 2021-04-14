package com.website.demo.doctor;

import com.website.demo.schedule.Schedule;
import com.website.demo.visit.Visit;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

//    public Doctor(DoctorRequest doctorRequest) {
//        super(doctorRequest.getFirstName(),
//                doctorRequest.getSecondName(),
//                doctorRequest.getLastName(),
//                doctorRequest.getEmail(),
//                doctorRequest.getPassword(),
//                doctorRequest.getPhone(),
//                AppUserRole.ADMIN);
//    }


    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visitSet;
    @ManyToMany
    @JoinTable(name = "doctor_schedule", joinColumns = {@JoinColumn(name = "doctor_id")}, inverseJoinColumns = {@JoinColumn(name = "schedule_id")})
    private Set<Schedule> schedules;
    @Id
    private Long id;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
