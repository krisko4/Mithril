package com.website.demo.dispensary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispensaryRepository extends JpaRepository<Dispensary, Long> {
    Optional<Dispensary> findByNameAndSpecializationName(String name, String specializationName);
}
