package com.website.demo.login;

import com.website.demo.security.utils.JWTUtil;
import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import com.website.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Service;



@Service
@Getter
@Setter
@AllArgsConstructor
public class LoginService {

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;
    private final JWTUtil jwtUtil;

    public LoginResponse login(String email, String password) {
        authenticate(email, password);
        AppUser appUser = (AppUser) appUserService.loadUserByUsername(email);
        String token =  jwtUtil.generateToken(appUser);
        return new LoginResponse(token, email, appUser.getFirstName(), appUser.getId());


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
