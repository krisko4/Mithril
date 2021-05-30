package com.website.demo.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
    public List<DoctorDto> getDoctorsBy(@RequestParam(required = false) String date) {
        return appUserService.getDoctorsBy(date).stream().map(DoctorDto::from).collect(Collectors.toList());
    }

    @GetMapping("doctors/except-for/{id}")
    public List<DoctorDto> getDoctorsBy(@PathVariable Long id) {
        return appUserService.getDoctorsExceptForOne(id);
    }
//
//    @GetMapping("nameByMail")
//    public String findFirstNameByEmail(@RequestParam String email){
//        return appUserService.findFirstNameByEmail(email);
//    }

//    @GetMapping("{id}/getName")
//    public String getNameById(@PathVariable Long id){
//        return appUserService.getNameById(id);
//    }




}

