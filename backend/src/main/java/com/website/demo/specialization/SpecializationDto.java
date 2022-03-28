package com.website.demo.specialization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SpecializationDto {

    private String name;


    public static SpecializationDto from(Specialization specialization){
        SpecializationDto specializationDto = new SpecializationDto();
        specializationDto.name = specialization.getName();
        return specializationDto;
    }

}
