package com.website.demo.specialization;

import com.website.demo.dispensary.Dispensary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    @Query(value = "select * from specialization where id in(\n" +
            "    select specialization_id from dispensary_specialization where dispensary_id = ?1\n" +
            ")", nativeQuery = true)
    List<Specialization> findSpecializationsForDispensary(Long id);


}
