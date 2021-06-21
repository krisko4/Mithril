package com.website.demo.registration;

import com.website.demo.authorities.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "admin/registration")
@AllArgsConstructor
public class RegistrationAdminController {

    private final RegistrationService registrationService;


    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
