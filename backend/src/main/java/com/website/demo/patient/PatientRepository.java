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



    @Transactional
    @Modifying
    @Query(value= "update patient set doctor_id = null where doctor_id = ?1 and id = ?2", nativeQuery = true)
    void removeDoctorForPatient(Long doctor_id, Long id);

    @Transactional
    @Modifying
    @Query(value = "update patient set doctor_id = ?1 where id = ?2", nativeQuery = true)
    void addDoctorForPatient(Long doctor_id, Long id);


    @Query("select new com.website.demo.patient.PatientDto(p.id, p.firstName, p.secondName, p.lastName, p.pesel, p.birthdate, p.phone, p.address, p.email, p.appUser.id) from patient p")
    List<PatientDto> findAllPatients();


    @Query("select new com.website.demo.patient.PatientDto(p.id, p.firstName, p.secondName, p.lastName, p.pesel, p.birthdate, p.phone, p.address, p.email, p.appUser.id) from patient p where p.firstName like :character%")
    List<PatientDto> findByFirstNameStartsWith(String character);

    @Query("select new com.website.demo.patient.PatientDto(p.id, p.firstName, p.secondName, p.lastName, p.pesel, p.birthdate, p.phone, p.address, p.email, p.appUser.id) from patient p where p.appUser.id = ?1")
    List<PatientDto> findByDoctorId(Long doctorId);

}
