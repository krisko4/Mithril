package com.website.demo.registration;

import antlr.Token;
import com.github.javafaker.App;
import com.website.demo.address.Address;
import com.website.demo.address.AddressService;
import com.website.demo.authorities.AppUserRole;
import com.website.demo.registration.token.ConfirmationToken;
import com.website.demo.registration.token.ConfirmationTokenRepository;
import com.website.demo.registration.token.ConfirmationTokenService;
import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "doctor/registration")
@AllArgsConstructor
public class RegistrationDoctorController {

    private final RegistrationService registrationService;
    private final ConfirmationTokenService confirmationTokenService;
    private final AddressService addressService;


    @PostMapping("test")
    public String test(@RequestBody @Valid RegistrationRequest request){
        AppUser appUser = new AppUser(request.getFirstName(), request.getSecondName());
        return "abc";
    }


    @PostMapping
    public String register(@ModelAttribute @Valid RegistrationRequest request){
        request.setRole(AppUserRole.DOCTOR);
        Address requestAddress = new Address(
                request.getCountry(),
                request.getCity(),
                request.getStreet(),
                request.getFlatNumber(),
                request.getPostCode()
        );
        AppUser appUser =   new AppUser(
                request.getFirstName(),
                request.getSecondName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhone(),
                addressService.save(requestAddress),
                registrationService.birthdayToLocalDate(request.getBirthdate()),
                request.getRole()
        );
        return registrationService.register(appUser, request.getImage());
    }


    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }




    @PostMapping("resendEmail")
    public void resendEmail(@RequestBody ConfirmationRequest confirmationRequest){
        registrationService.resendEmail(confirmationRequest.getEmail(), confirmationRequest.getFirstName());
    }


    @PostMapping("resendEmailToken")
    public void resendEmailToken(@RequestBody TokenRequest tokenRequest){
        AppUser appUser = confirmationTokenService.getUserByToken(tokenRequest.getToken());
        registrationService.resendEmail(appUser.getEmail(), appUser.getFirstName());
    }








}
