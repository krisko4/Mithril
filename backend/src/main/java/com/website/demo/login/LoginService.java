package com.website.demo.login;

import com.website.demo.security.utils.JWTUtil;
import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import com.website.demo.user.AppUserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;


@Service
@Getter
@Setter
@AllArgsConstructor
public class LoginService {

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;
    private final JWTUtil jwtUtil;

    public String login(String email, String password) {
        authenticate(email, password);
        AppUser appUser = (AppUser) appUserService.loadUserByUsername(email);
        return jwtUtil.generateToken(appUser);


    }

    private void authenticate(String email, String password) {
        try {
            daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new RuntimeException("This user has not been activated");
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
