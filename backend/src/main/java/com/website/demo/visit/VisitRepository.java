package com.website.demo.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query(value = "select * from visit where FORMAT(date, 'yyyy-MM-dd') = ?1 and app_user_id = ?2 and finished = ?3", nativeQuery = true)
    List<Visit> findAllVisitsForOneDoctorByDateAndFinished(LocalDate date, Long id, Boolean finished);

    @Query(value = "select * from visit where FORMAT(date, 'yyyy-MM-dd') = ?1 and app_user_id = ?2", nativeQuery = true)
    List<Visit> findAllVisitsForOneDoctorByDate(LocalDate date, Long id);

    List<Visit> findByPatient_id(Long patientID);

    List<Visit> findByPatient_idAndDoctor_idAndFinished(Long patientID, Long doctorID, Boolean finished);
    List<Visit> findByPatient_idAndDoctor_id(Long patientID, Long doctorID);

    @Query(value = "select * from visit where doctor_id = ?1", nativeQuery = true)
    List<Visit> findAllByDoctorId(Long id);

    List<Visit> findByPatient_idAndFinished(Long patientID, Boolean finished);
}
