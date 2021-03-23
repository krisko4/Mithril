package com.website.demo.doctor;

import com.website.demo.schedule.Schedule;
import com.website.demo.schedule.ScheduleDto;
import com.website.demo.visit.Visit;
import com.website.demo.visit.VisitService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@Data
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final VisitService visitService;


    /**
     * Method finding all doctors available on provided date. Then, for each doctor, the difference between start_hour
     * and end_hour is converted into a list of working hours.
     *
     * @param date date provided by the user
     * @return a list of doctors available on provided date with a list of available hours for each doctor during that period
     */
    public List<DoctorVisitResponse> getAvailableDoctorsByDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d", Locale.US);
        LocalDate localDate = LocalDate.parse(date, formatter);
        // lista doktorów pracujących w wybranym dniu
        List<Doctor> doctorList = doctorRepository.findAllBySchedules_Date(localDate);
        List<DoctorVisitResponse> dvrList = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            // lista wizyt w danym dniu dla danego doktora
            List<Visit> visitList = visitService.getVisitsByDate(date, doctor.getId());
            // lista godzin w jakich odbywają się wizyty
            List<LocalTime> visitHourList = new ArrayList<>();
            for (Visit visit : visitList) {
                int hour = visit.getDate().getHour();
                int minute = visit.getDate().getMinute();
                LocalTime visitHour = LocalTime.of(hour, minute);
                visitHourList.add(visitHour);
            }
            // sprawdzamy dni pracy doktora - jeśli data jest inna niż ta, którą wybraliśmy, szukamy dalej
            for (Schedule schedule : doctor.getSchedules()) {
                if (schedule.getDate().compareTo(localDate) != 0) {
                    continue;
                }
                LocalTime startHour = schedule.getStartHour();
                LocalTime endHour = schedule.getEndHour();
                List<LocalTime> hourList = new ArrayList<>();
                while (startHour.isBefore(endHour)) {
                    if (!visitHourList.contains(startHour)){
                        hourList.add(startHour);
                }
                    startHour = startHour.plusMinutes(20);
                }
                DoctorVisitResponse dvr = new DoctorVisitResponse(DoctorDto.from(doctor), ScheduleDto.from(schedule), hourList);
                dvrList.add(dvr);
            }
        }
        return dvrList;
    }

}
