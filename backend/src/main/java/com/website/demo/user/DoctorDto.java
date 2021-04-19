package com.website.demo.user;

import com.github.javafaker.App;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDto {

    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private String phone;
    private String imageName;
//    private Set<VisitDto> visitSet;
 //   private Set<ScheduleDto> schedules;

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
