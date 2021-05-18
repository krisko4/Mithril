package com.website.demo.notice;

import com.website.demo.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value = "select top 3 * from notice order by date desc", nativeQuery = true)
    List<Notice> findTopThree();

    @Query(value = "select top 3 * from notice where date < ?1 order by date desc ", nativeQuery = true)
    List<Notice> findBeforeDate(LocalDateTime date);
}
