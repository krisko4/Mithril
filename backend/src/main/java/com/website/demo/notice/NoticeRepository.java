package com.website.demo.notice;

import com.website.demo.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value = "select top 4 * from notice order by date desc", nativeQuery = true)
    List<Notice> findTopFour();

    @Query(value = "select top 4 * from notice where date < ?1 order by date desc ", nativeQuery = true)
    List<Notice> findBeforeDate(String date);
    @Query(value = "select top 4 * from notice where date > ?1 order by date desc ", nativeQuery = true)
    List<Notice> findAfterDate(String date);
}
