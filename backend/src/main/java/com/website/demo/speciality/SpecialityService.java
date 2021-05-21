package com.website.demo.speciality;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class SpecialityService {

    private final SpecialityRepository specialityRepository;

    public List<Speciality> getSpecialities() {
        return specialityRepository.findAll();
    }

    public List<Speciality> getSpecialitiesForDispensary(Long id) {
        return specialityRepository.findByDispensary_id(id);
    }
}
