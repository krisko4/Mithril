package com.website.demo.speciality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

    List<Speciality> findByDispensary_id(Long id);
}
