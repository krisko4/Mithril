package com.website.demo.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByFirstNameStartsWith(String character);

    @Query(value = "select * from patient where doctor_id=?1", nativeQuery = true)
    List<Patient> findByDoctorId(Long doctorID);

    @Transactional
    @Modifying
    @Query(value= "update patient set doctor_id = null where doctor_id = ?1 and id = ?2", nativeQuery = true)
    void removeDoctorForPatient(Long doctor_id, Long id);

    @Transactional
    @Modifying
    @Query(value = "update patient set doctor_id = ?1 where id = ?2", nativeQuery = true)
    void addDoctorForPatient(Long doctor_id, Long id);
}
