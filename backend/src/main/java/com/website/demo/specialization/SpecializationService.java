package com.website.demo.specialization;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }


    public List<Specialization> getAll() {
        return specializationRepository.findAll();
    }

    public List<Specialization> getSpecializationsForDispensary(Long id) {
        return specializationRepository.findSpecializationsForDispensary(id);
    }
}
