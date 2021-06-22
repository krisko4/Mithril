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



    @GetMapping("first-name")
    public String findFirstNameByEmail(@RequestParam String email){
        return appUserService.findFirstNameByEmail(email);
    }

    @GetMapping("except-for/{id}")
    public List<AppUserDto> findUsersExceptForOne(@PathVariable Long id){
        return appUserService.findUsersExceptForOne(id);
    }

//    @GetMapping("{id}/getName")
//    public String getNameById(@PathVariable Long id){
//        return appUserService.getNameById(id);
//    }




}

