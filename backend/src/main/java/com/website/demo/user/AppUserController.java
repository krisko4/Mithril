package com.website.demo.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("is_email_taken")
    public boolean isEmailTaken(@RequestParam("email") String email){
        return appUserService.isEmailTaken(email);
    }

    @GetMapping
    public List<AppUser> list(){
        return appUserService.list();
    }
}

