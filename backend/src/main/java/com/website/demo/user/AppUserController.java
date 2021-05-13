package com.website.demo.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@CrossOrigin
@RestController
@RequestMapping("users")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;


    @GetMapping("email")
    public void checkEmailAvailability(@RequestParam("email") String email) {
        appUserService.checkEmailAvailability(email);
    }

    @GetMapping
    public List<AppUser> list() {
        return appUserService.list();
    }

    @GetMapping("doctors")
    public List<DoctorDto> getAvailableDoctorsByDate(@RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return appUserService.getAvailableDoctorsByDate(localDate);
    }

    @GetMapping("nameByMail")
    public String findFirstNameByEmail(@RequestParam String email){
        return appUserService.findFirstNameByEmail(email);
    }

    @GetMapping("{id}/getName")
    public String getNameById(@PathVariable Long id){
        return appUserService.getNameById(id);
    }




}

