package com.website.demo.patient;

import com.website.demo.authorities.AppUserRole;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;

@Data
public class NewPatientRequest {

    private Long doctorId;
    @Pattern(regexp="^[A-Za-z\\p{L}]+$")
    private final String firstName;
    @Pattern(regexp="^[A-Za-z\\p{L}]*$")
    private final String secondName;
    @Pattern(regexp="^[A-Za-z\\p{L}]+$")
    private final String lastName;
    @Pattern(regexp="^[0-9]*$")
    private final String phone;
    @Pattern(regexp="^[A-Za-z\\p{L}]+$")
    private final String country;
    @Pattern(regexp="^[A-Za-z\\p{L}]+$")
    private final String city;
    @Pattern(regexp="^[a-zA-Z\\p{L}]+( [0-9]+[a-z]?)?")
    private final String street;
    private final Integer flatNumber;
    @Pattern(regexp="^([0-9]{2})(-[0-9]{3})?$")
    private final String postCode;
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")
    private final String birthdate;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    private final String email;

    private final String pesel;


}
