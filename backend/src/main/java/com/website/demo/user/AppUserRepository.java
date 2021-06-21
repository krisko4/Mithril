package com.website.demo.user;

import com.website.demo.user.doctor.DoctorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Query("select u.firstName from AppUser u where u.email = ?1")
    Optional<String> findFirstNameByEmail(String email);


    boolean existsByEmail(String email);

    @Query("select a from AppUser a")
    List<AppUser> findAllDoctors();

//    @Query("select new com.website.demo.user.doctor.DoctorDto(a.firstName, a.secondName, a.lastName, a.imageName,  a.email, a.phone, a.id) from AppUser a")
//    List<DoctorDto> findAllDoctors();

    Optional<AppUser> findByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = true WHERE a.email = ?1")
    void enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.imageName = ?1 WHERE a.email = ?2")
    void setImageName(String imagePath, String email);

    @Query("select new com.website.demo.user.doctor.DoctorDto(a.firstName, a.secondName, a.lastName, a.imageName,  a.email, a.phone, a.id) from AppUser a join a.schedules schedule where schedule.date = ?1")
    List<DoctorDto> findAllByScheduleDate(String date);


    @Query("select new com.website.demo.user.doctor.DoctorDto(a.firstName, a.secondName, a.lastName, a.imageName, a.id) from AppUser a where a.id <> ?1")
    List<DoctorDto> findAllExceptFor(Long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO doctor_schedule VALUES (?2, ?1)", nativeQuery = true)
    void setSchedule(Long scheduleID, Long id);
}
