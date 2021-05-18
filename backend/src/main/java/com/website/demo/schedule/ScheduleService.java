package com.website.demo.schedule;

import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import com.website.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
@CrossOrigin
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AppUserService appUserService;


    public List<Schedule> findSchedulesForDoctorBy(String date, Long doctorID) {
        if (date != null) {
            List<Schedule> scheduleList = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
            LocalDate localDate = LocalDate.parse(date, formatter);
            Long scheduleID = scheduleRepository.findScheduleIDForDoctorByDate(localDate, doctorID);
            if(scheduleID != null){
                scheduleList.add(scheduleRepository.findById(scheduleID).get());
            }
            return scheduleList;
        }
        return scheduleRepository.findAllByDoctorID(doctorID);

    }

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public void setSchedule(String date, String startHour, String endHour, int breakDuration, int visitDuration, Long doctorID) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
        LocalDate localDate = LocalDate.parse(date, formatter);
        Schedule schedule;
        if (!findSchedulesForDoctorBy(date, doctorID).isEmpty()) {
            schedule = findSchedulesForDoctorBy(date, doctorID).get(0);
        } else {
            schedule = new Schedule();
        }
        schedule.setDate(localDate);
        schedule.setStartHour(startHour);
        schedule.setEndHour(endHour);
        schedule.setBreakDuration(breakDuration);
        schedule.setVisitDuration(visitDuration);
        scheduleRepository.save(schedule);
        appUserService.setSchedule(schedule, doctorID);


    }

    public void deleteSchedulesForDoctorBy(String date,
                                           Long doctorId) {
        if(date == null){
            scheduleRepository.deleteById(doctorId);
            return;
        }
        Schedule schedule = findSchedulesForDoctorBy(date, appUserService.findById(doctorId).getId()).get(0);
        scheduleRepository.deleteByDateAndId(doctorId, schedule.getId());

    }
}
