package com.website.demo.registration;

import antlr.Token;
import com.github.javafaker.App;
import com.website.demo.authorities.AppUserRole;
import com.website.demo.registration.token.ConfirmationToken;
import com.website.demo.registration.token.ConfirmationTokenRepository;
import com.website.demo.registration.token.ConfirmationTokenService;
import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "doctor/registration")
@AllArgsConstructor
public class RegistrationDoctorController {

    private final RegistrationService registrationService;
    private final ConfirmationTokenService confirmationTokenService;
    private final AppUserRepository appUserRepository;

    @CrossOrigin
    @PostMapping
    public String register(@ModelAttribute RegistrationRequest request){
        request.setRole(AppUserRole.DOCTOR);
        return registrationService.register(request);
    }

    @CrossOrigin
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

    @GetMapping("tokenbyuserid")
    public Optional<AppUser> getAllConfirmationTokensByUserId(@RequestParam("id") Long id){
        return appUserRepository.findById(id);
    }

    @CrossOrigin
    @PostMapping("resendEmail")
    public void resendEmail(@RequestBody RegistrationRequest registrationRequest){
        registrationService.resendEmail(registrationRequest.getEmail(), registrationRequest.getFirstName());
    }

    @CrossOrigin
    @PostMapping("resendEmailToken")
    public void resendEmailToken(@RequestBody TokenRequest tokenRequest){
        AppUser appUser = confirmationTokenService.getUserByToken(tokenRequest.getToken());
        registrationService.resendEmail(appUser.getEmail(), appUser.getFirstName());
    }








}
