package com.website.demo.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query(value = "select * from visit where FORMAT(date, 'yyyy-MM-dd') = ?1 and app_user_id = ?2 and finished = ?3", nativeQuery = true)
    List<Visit> findAllVisitsForOneDoctorByDateAndFinished(LocalDate date, Long id, Boolean finished);

    @Query(value = "select * from visit where FORMAT(date, 'yyyy-MM-dd') = ?1 and app_user_id = ?2", nativeQuery = true)
    List<Visit> findAllVisitsForOneDoctorByDate(LocalDate date, Long id);

    List<Visit> findByPatient_id(Long patientID);

    List<Visit> findByPatient_idAndDoctor_IdAndFinished(Long patientID, Long doctorID, Boolean finished);

    List<Visit> findByPatient_idAndDoctor_Id(Long patientID, Long doctorID);


    List<Visit> findByDoctor_Id(Long doctorId);

    List<Visit> findByPatient_idAndFinished(Long patientID, Boolean finished);

    @Transactional
    @Modifying
    @Query(value = "insert into visit values (?1, 0, ?2, ?3, ?4)", nativeQuery = true)
    void saveNewVisit(String date, int duration, Long doctorId, Long patientId);

    @Query(value = "select * from visit where patient_id=?1 and app_user_id=?2 and date=?3", nativeQuery = true)
    Optional<Visit> findVisitByPatientAndDoctorAndDate(Long patientId, Long doctorId, String date);


}
