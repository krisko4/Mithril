package com.website.demo.user;

import com.website.demo.user.doctor.DoctorDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendDto {

    private String firstName;
    private String secondName;
    private String lastName;
    private String imageName;
    private Long id;

    public FriendDto(String firstName, String secondName, String lastName, String imageName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.imageName = imageName;
    }

    public static FriendDto from(DoctorDto doctorDto){
        FriendDto friendDto = new FriendDto();
        friendDto.firstName = doctorDto.getFirstName();
        friendDto.secondName = doctorDto.getSecondName();
        friendDto.lastName = doctorDto.getLastName();
        friendDto.imageName = doctorDto.getImageName();
        friendDto.id = doctorDto.getId();
        return friendDto;
    }

}
