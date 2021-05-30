package com.website.demo.user;

import com.github.javafaker.App;
import com.website.demo.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByPassword(String password);

    boolean existsByEmail(String email);



    Optional<AppUser> findByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = true WHERE a.email = ?1")
    int enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.imageName = ?1 WHERE a.email = ?2")
    void setImageName(String imagePath, String email);

    List<AppUser> findAllBySchedules_Date(String date);

    @Query("select new com.website.demo.user.DoctorDto(a.firstName, a.secondName, a.lastName, a.imageName) from AppUser a where a.id <> ?1")
    List<DoctorDto> findAllExceptFor(Long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO doctor_schedule VALUES (?2, ?1)", nativeQuery = true)
    void setSchedule(Long scheduleID, Long id);
}
