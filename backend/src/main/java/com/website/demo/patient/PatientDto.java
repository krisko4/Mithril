package com.website.demo.patient;

import com.website.demo.address.Address;
import com.website.demo.address.AddressDto;
import com.website.demo.user.AppUser;
import com.website.demo.visit.Visit;
import com.website.demo.visit.VisitDto;
import lombok.Data;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PatientDto {

    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String pesel;
    private LocalDate birthdate;
    private String phone;
    private Address address;
    private String email;
    private Long doctorId;
   // private Set<VisitDto> visit;

    public PatientDto(Long id,
                      String firstName,
                      String secondName,
                      String lastName,
                      String pesel,
                      LocalDate birthdate,
                      String phone,
                      Address address,
                      String email,
                      Long doctorId) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.birthdate = birthdate;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.doctorId = doctorId;
    }


    public PatientDto(Patient patient){
        this.id = patient.getId();
        this.firstName = patient.getFirstName();
        this.secondName = patient.getSecondName();
        this.lastName = patient.getLastName();
        this.pesel = patient.getPesel();
        this.birthdate = patient.getBirthdate();
        this.phone = patient.getPhone();
        this.address = patient.getAddress();
        this.email = patient.getEmail();
        this.doctorId = patient.getAppUser().getId();

    }

}
