package com.website.demo.registration;

import com.website.demo.authorities.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "admin/registration")
@AllArgsConstructor
public class RegistrationAdminController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        request.setRole(AppUserRole.ADMIN);
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
