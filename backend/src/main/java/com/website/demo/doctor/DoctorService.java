package com.website.demo.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class DoctorService {

private final DoctorRepository doctorRepository;


    public List<Doctor> getDoctors() {
        return doctorRepository.findAllBySchedules_Date(Date.valueOf("1995-04-28"));
    }
}
