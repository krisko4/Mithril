package com.website.demo.user.doctor;

import com.website.demo.specialization.SpecializationDto;
import com.website.demo.user.AppUserRepository;
import com.website.demo.user.AppUserDto;
import com.website.demo.visit.VisitService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
public class DoctorService {

    private final AppUserRepository appUserRepository;
    private final VisitService visitService;

    public List<DoctorDto> getDoctorsBy(String date){
        List<DoctorDto> doctors;
        if(date == null){
            doctors = appUserRepository.findAllDoctors();
        }
        else{
            doctors = appUserRepository.findAllByScheduleDate(date);
        }
        for(DoctorDto doctor : doctors){
            Set<SpecializationDto> specializationDtoSet = appUserRepository.getSpecializationsForDoctor(doctor.getId())
                    .stream()
                    .map(SpecializationDto::from)
                    .collect(Collectors.toSet());
            doctor.setSpecializations(specializationDtoSet);
        }
        return doctors;
    }

//    public List<AppUserDto> getDoctorsExceptForOne(Long id){
//        return appUserRepository.findAllExceptFor(id)
//                .stream()
//                .map(AppUserDto::from)
//                .collect(Collectors.toList());
//    }

}