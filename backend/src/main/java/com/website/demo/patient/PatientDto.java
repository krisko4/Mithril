package com.website.demo.patient;

import com.website.demo.address.Address;
import com.website.demo.address.AddressDto;
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
    private LocalDate birthDate;
    private String phone;
    private AddressDto address;
   // private Set<VisitDto> visit;

    public static PatientDto from(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.id = patient.getId();
        patientDto.firstName = patient.getFirstName();
        patientDto.secondName = patient.getSecondName();
        patientDto.lastName = patient.getLastName();
        patientDto.pesel = patient.getPesel();
        patientDto.birthDate = patient.getBirthDate();
        patientDto.phone = patient.getPhone();
        patientDto.address = AddressDto.from(patient.getAddress());
       // patientDto.visit = patient.getVisitSet().stream().map(VisitDto::new).collect(Collectors.toSet());
        return patientDto;
    }

}
