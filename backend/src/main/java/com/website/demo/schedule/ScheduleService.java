package com.website.demo.schedule;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    
 //   public Schedule findScheduleForDoctorByDate(LocalDate date, Long id){
    //    return scheduleRepository.findScheduleForDoctorByDate(date, id);
 //   }

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }
}
