package com.website.demo.login;


import com.website.demo.authorities.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {

    private String token;
    private String email;
    private String firstName;
    private String secondName;
    private String lastName;
    private Long id;
    private String imageName;
    private AppUserRole role;
}
