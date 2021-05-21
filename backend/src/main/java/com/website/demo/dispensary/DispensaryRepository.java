package com.website.demo.dispensary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispensaryRepository extends JpaRepository<Dispensary, Long> {
}
