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

    private String first_name;
    private String second_name;
    private String last_name;
    private String pesel;
    private LocalDate birth_date;
    private String phone;
    private AddressDto address;
    private Set<VisitDto> visit;

    public static PatientDto from(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.first_name = patient.getFirst_name();
        patientDto.second_name = patient.getSecond_name();
        patientDto.last_name = patient.getLast_name();
        patientDto.pesel = patient.getPesel();
        patientDto.birth_date = patient.getBirth_date();
        patientDto.phone = patient.getPhone();
        patientDto.address = AddressDto.from(patient.getAddress());
        patientDto.visit = patient.getVisitSet().stream().map(VisitDto::new).collect(Collectors.toSet());
        return patientDto;
    }

}
