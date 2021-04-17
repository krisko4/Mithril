package com.website.demo.registration;

import com.website.demo.address.Address;
import com.website.demo.authorities.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String firstName;
    private final String secondName;
    private final String lastName;
    private final String phone;
    private final String password;
    private final String email;
    private final String country;
    private final String city;
    private final String street;
    private final Integer flatNumber;
    private final String postCode;
    private final String birthdate;
    private MultipartFile image;
    private AppUserRole role;

    public void setRole(AppUserRole role) {
        this.role = role;
    }
}
