package com.website.demo.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "select schedule_id from doctor_schedule where schedule_id in (\n" +
            "    select id from schedule where date = ?1\n" +
            ") and doctor_id = ?2", nativeQuery = true)
    Long findScheduleIDForDoctorByDate(LocalDate date, Long doctor_id);

}
