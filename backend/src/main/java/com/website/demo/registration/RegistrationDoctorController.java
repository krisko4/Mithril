package com.website.demo.registration;

import com.website.demo.authorities.AppUserRole;
import com.website.demo.registration.token.ConfirmationToken;
import com.website.demo.registration.token.ConfirmationTokenRepository;
import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "doctor/registration")
@AllArgsConstructor
public class RegistrationDoctorController {

    private final RegistrationService registrationService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AppUserRepository appUserRepository;

    @CrossOrigin
    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        request.setRole(AppUserRole.DOCTOR);
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

    @GetMapping("tokenbyuserid")
    public Optional<AppUser> getAllConfirmationTokensByUserId(@RequestParam("id") Long id){
        return appUserRepository.findById(id);
    }
}
