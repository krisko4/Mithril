package com.website.demo.user.doctor;

import com.website.demo.schedule.Schedule;
import com.website.demo.specialization.Specialization;
import com.website.demo.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto {

    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private String phone;
    private String imageName;
    private Set<Specialization> specializations;
//    private Set<VisitDto> visitSet;
    private Set<Schedule> schedules;


    public DoctorDto(String firstName,
                     String secondName,
                     String lastName,
                     String imageName,
                     String email,
                     String phone,
                     Long id
                     ) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.imageName = imageName;
        this.phone = phone;
        this.email = email;

    }

    public DoctorDto(String firstName,
                     String secondName,
                     String lastName,
                     String imageName,
                     String email,
                     String phone,
                     Long id,
                     Set<Specialization> specializations
    ) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.imageName = imageName;
        this.phone = phone;
        this.email = email;
        this.specializations = specializations;

    }

    public DoctorDto(String firstName,
                     String secondName,
                     String lastName,
                     String imageName,
                     Long id) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.imageName = imageName;

    }

    public static DoctorDto from(AppUser appUser) {
        DoctorDto dto = new DoctorDto();
        dto.id = appUser.getId();
        dto.firstName = appUser.getFirstName();
        dto.secondName = appUser.getSecondName();
        dto.lastName = appUser.getLastName();
        dto.email = appUser.getEmail();
        dto.phone = appUser.getPhone();
        dto.imageName = appUser.getImageName();

  //      dto.visitSet = doctor.getVisitSet().stream().map(VisitDto::new).collect(Collectors.toSet());
  //      dto.schedules = doctor.getSchedules().stream().map(ScheduleDto::from).collect(Collectors.toSet());

        return dto;
    }


}
