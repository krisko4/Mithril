package com.website.demo.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query(value = "select * from visit where cast(date as DATE) = ?1 and doctor_id = ?2", nativeQuery = true)
    List<Visit> findAllVisitsForOneDoctorByDate(String date, Long id);
}
