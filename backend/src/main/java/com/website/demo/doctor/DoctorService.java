package com.website.demo.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@Data
public class DoctorService {

private final DoctorRepository doctorRepository;


    public List<Doctor> getAvailableDoctors(String dateString) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-d", Locale.US);
        LocalDate localDate = LocalDate.parse(dateString, formatter1);
        return doctorRepository.findAllBySchedules_Date(localDate);
    }
}
