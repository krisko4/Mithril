package com.website.demo.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @CrossOrigin
    @GetMapping("/email")
    public void isEmailTaken(@RequestParam("email") String email){
        appUserService.checkEmailAvailability(email);
    }

    @GetMapping
    public List<AppUser> list(){
        return appUserService.list();
    }
}

