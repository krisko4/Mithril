package com.website.demo.visit;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class VisitService {

    private final VisitRepository visitRepository;

    public void addVisit() {
        Visit visit = new Visit();
        visitRepository.save(visit);
    }

    public List<Visit> getVisits() {
       return visitRepository.findAll();
    }
}
