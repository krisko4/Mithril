package com.website.demo.registration;

import com.website.demo.address.Address;
import com.website.demo.address.AddressService;
import com.website.demo.authorities.AppUserRole;
import com.website.demo.registration.ConfirmationRequest;
import com.website.demo.registration.RegistrationRequest;
import com.website.demo.registration.RegistrationService;
import com.website.demo.registration.TokenRequest;
import com.website.demo.registration.token.ConfirmationTokenService;
import com.website.demo.specialization.SpecializationService;
import com.website.demo.user.AppUser;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Data
@CrossOrigin
@RequestMapping("registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AddressService addressService;
    private final ConfirmationTokenService confirmationTokenService;
    private final SpecializationService specializationService;

    @PostMapping
    public void register(@ModelAttribute @Valid RegistrationRequest request){
        AppUserRole role;
        if(request.getRegistrationType().equals("doctorType")){
            role = AppUserRole.DOCTOR;
        }
        else{
            role = AppUserRole.RECEPTION_WORKER;
        }
        Address requestAddress = new Address(
                request.getCountry(),
                request.getCity(),
                request.getStreet(),
                request.getFlatNumber(),
                request.getPostCode()
        );
        AppUser appUser =  new AppUser(
                request.getFirstName(),
                request.getSecondName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhone(),
                addressService.getAddressAndSaveIfNotExists(requestAddress.getCountry(),
                        requestAddress.getCity(),
                        requestAddress.getStreet(),
                        requestAddress.getFlatNumber(),
                        requestAddress.getPostCode()),
                registrationService.birthdayToLocalDate(request.getBirthdate()),
                role
        );
        registrationService.register(appUser, request.getImage());
        if(role == AppUserRole.DOCTOR){
            specializationService.setSpecializationsForDoctor(appUser, request.getSpecializationNames());
        }
    }


    @GetMapping("confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }



    @PostMapping("resend-email")
    public void resendEmail(@RequestBody ConfirmationRequest confirmationRequest){
        registrationService.resendEmail(confirmationRequest.getEmail(), confirmationRequest.getFirstName());
    }


    @PostMapping("resend-token")
    public void resendEmailToken(@RequestBody TokenRequest tokenRequest){
        AppUser appUser = confirmationTokenService.getUserByToken(tokenRequest.getToken());
        registrationService.resendEmail(appUser.getEmail(), appUser.getFirstName());
    }



}
