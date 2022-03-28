package com.website.demo.examination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {



    Optional<Examination> findByVisit_id(Long id);
}
