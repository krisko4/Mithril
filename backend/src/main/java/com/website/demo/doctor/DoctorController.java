package com.website.demo.doctor;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequestMapping("/doctors")

public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }



}
