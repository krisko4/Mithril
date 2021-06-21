package com.website.demo.user.doctor;

import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import com.website.demo.user.FriendDto;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class DoctorService {

    private final AppUserRepository appUserRepository;

    public List<DoctorDto> getDoctorsBy(String date){
        if(date == null){
            List<AppUser> userList = appUserRepository.findAllDoctors();
            int x = 5;
           // return appUserRepository.findAllDoctors();
        }
        List<DoctorDto> userList = appUserRepository.findAllByScheduleDate(date);
        return userList;
    }

    public List<FriendDto> getDoctorsExceptForOne(Long id){
        return appUserRepository.findAllExceptFor(id).stream().map(FriendDto::from).collect(Collectors.toList());
    }

}
