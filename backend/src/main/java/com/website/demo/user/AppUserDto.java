package com.website.demo.user;

import com.website.demo.user.doctor.DoctorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {

    private String firstName;
    private String secondName;
    private String lastName;
    private String imageName;
    private Long id;



//    public static AppUserDto from(DoctorDto doctorDto){
//        AppUserDto appUserDto = new AppUserDto();
//        appUserDto.firstName = doctorDto.getFirstName();
//        appUserDto.secondName = doctorDto.getSecondName();
//        appUserDto.lastName = doctorDto.getLastName();
//        appUserDto.imageName = doctorDto.getImageName();
//        appUserDto.id = doctorDto.getId();
//        return appUserDto;
//    }

}
