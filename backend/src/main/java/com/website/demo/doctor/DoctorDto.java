package com.website.demo.doctor;

import com.website.demo.schedule.Schedule;
import com.website.demo.schedule.ScheduleDto;
import com.website.demo.visit.Visit;
import com.website.demo.visit.VisitDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class DoctorDto {

    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private String phone;
//    private Set<VisitDto> visitSet;
 //   private Set<ScheduleDto> schedules;

    public static DoctorDto from(Doctor doctor) {
        DoctorDto dto = new DoctorDto();
        dto.id = doctor.getId();
        dto.firstName = doctor.getFirstName();
        dto.secondName = doctor.getSecondName();
        dto.lastName = doctor.getLastName();
        dto.email = doctor.getEmail();
        dto.phone = doctor.getPhone();
  //      dto.visitSet = doctor.getVisitSet().stream().map(VisitDto::new).collect(Collectors.toSet());
  //      dto.schedules = doctor.getSchedules().stream().map(ScheduleDto::from).collect(Collectors.toSet());

        return dto;
    }
}
