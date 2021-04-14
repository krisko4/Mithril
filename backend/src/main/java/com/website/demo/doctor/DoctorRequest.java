package com.website.demo.doctor;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class DoctorRequest {

    private String password;
    private String confirmPassword;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private String confirmEmail;
    private String phone;

}
