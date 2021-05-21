package com.website.demo.speciality;


import lombok.Data;
import lombok.experimental.PackagePrivate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@CrossOrigin
public class SpecialityController {

    private final SpecialityService specialityService;

    @GetMapping("specialities")
    public List<Speciality> getSpecialities(){
        return specialityService.getSpecialities();
    }

    @GetMapping("dispensaries/{id}/specialities")
    public List<Speciality> getSpecialitiesForDispensary(@PathVariable Long id){
        return specialityService.getSpecialitiesForDispensary(id);
    }
}
