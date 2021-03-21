package com.website.demo.visit;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;


    @PostMapping("/add")
    public void addVisit(){
        visitService.addVisit();
    }

    @GetMapping
    public List<Visit> getVisits(){
        return visitService.getVisits();
    }

}
