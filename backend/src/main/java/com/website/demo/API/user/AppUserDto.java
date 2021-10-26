package com.website.demo.API.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
public class AppUserDto {


    private String firstName;
    private String secondName;
    private String lastName;
    private String imageName;
    private Integer id;


    public AppUserDto(String firstName, String secondName, String lastName, String imageName, Integer id) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.imageName = imageName;
        this.id = id;
    }

    public AppUserDto(AppUser appUser){
        this.firstName = appUser.getPerson().getFirstName();
        this.secondName = appUser.getPerson().getSecondName();
        this.lastName = appUser.getPerson().getLastName();
        this.imageName = appUser.getImg();
        this.id = appUser.getId();
    }





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
