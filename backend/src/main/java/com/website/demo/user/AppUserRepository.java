package com.website.demo.user;

import com.github.javafaker.App;
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

    List<DoctorDto> findAllBySchedules_Date(LocalDate date);
}
