package com.website.demo.medication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByNameStartsWith(String character);

    @Transactional
    @Modifying
    @Query(value = "insert into prescription_medication values (?1, ?2)", nativeQuery = true)
    void addMedicationToPrescription(Long prescriptionId, Long medicationId);
}
