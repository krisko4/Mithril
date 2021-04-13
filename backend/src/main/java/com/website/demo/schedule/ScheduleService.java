package com.website.demo.schedule;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    
    public Schedule findScheduleForDoctorByDate(String date, Long doctor_id){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d", Locale.US);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return scheduleRepository.findById(scheduleRepository.findScheduleIDForDoctorByDate(localDate, doctor_id)).orElseThrow(()-> null);
    }

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }
}
