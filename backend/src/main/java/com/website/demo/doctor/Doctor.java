package com.website.demo.doctor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.website.demo.schedule.Schedule;
import com.website.demo.user.CustomUser;
import com.website.demo.visit.Visit;
import lombok.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor  {



    public Doctor(DoctorRequest doctorRequest){
        this.firstName = doctorRequest.getFirstName();
        this.secondName = doctorRequest.getSecondName();
        this.lastName = doctorRequest.getLastName();
        this.phone = doctorRequest.getPhone();
        this.password = doctorRequest.getPassword();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String username;
    private String password;
    private String email;
    private String secondName;
    private String lastName;
    private String phone;
    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visitSet;
    @ManyToMany
    @JoinTable(
            name = "doctor_schedule",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "schedule_id")}
    )
    private Set<Schedule> schedules;



}
