package com.website.demo.doctor;

import com.website.demo.schedule.Schedule;
import com.website.demo.schedule.ScheduleDto;
import com.website.demo.visit.Visit;
import com.website.demo.visit.VisitDto;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.Set;
import java.util.stream.Collectors;

public class DoctorDto {

    private Long id;
    private String first_name;
    private String second_name;
    private String last_name;
    private String email;
    private String phone;
    private Set<VisitDto> visitSet;
    private Set<ScheduleDto> schedules;

    public static DoctorDto from(Doctor doctor) {
        DoctorDto dto = new DoctorDto();

        dto.id = doctor.getId();
        dto.first_name = doctor.getFirst_name();
        dto.second_name = doctor.getSecond_name();
        dto.last_name = doctor.getLast_name();
        dto.email = doctor.getEmail();
        dto.phone = doctor.getPhone();
        dto.visitSet = doctor.getVisitSet().stream().map(VisitDto::new).collect(Collectors.toSet());
        dto.schedules = doctor.getSchedules().stream().map(ScheduleDto::from).collect(Collectors.toSet());

        return dto;
    }
}
