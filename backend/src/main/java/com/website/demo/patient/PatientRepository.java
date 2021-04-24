package com.website.demo.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<PatientDto> findByFirstNameStartsWith(String character);

    @Query(value = "select * from patient where doctor_id=?1", nativeQuery = true)
    List<Patient> findByDoctor(Long doctor_id);
}
