package com.website.demo.user;

import com.website.demo.registration.token.ConfirmationToken;
import com.website.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {



    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final static String EMAIL_TAKEN_MSG = "email %s is taken";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String findFirstNameByEmail(String email){
        return appUserRepository.findByEmail(email).get().getFirstName();
    }



    public String signUp(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException(String.format(EMAIL_TAKEN_MSG, appUser.getEmail()));
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        AppUser save = appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                save
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);


        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }


    public void checkEmailAvailability(String email) {
        if (appUserRepository.existsByEmail(email)) throw new IllegalStateException("E-mail already exists");
    }

    public void setImageName(String imageName, String email){
        appUserRepository.setImageName(imageName, email);
    }

    public List<AppUser> list() {
        return appUserRepository.findAll();
    }


    public List<DoctorDto> getAvailableDoctorsByDate(LocalDate date){
        return appUserRepository.findAllBySchedules_Date(date);
    }


}
