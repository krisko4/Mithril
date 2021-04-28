

package com.website.demo.visit;

import com.website.demo.patient.Patient;
import com.website.demo.patient.PatientDto;
import com.website.demo.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitDto {

    public LocalDateTime date;
    public String description;
    private String patientFirstName;
    private String patientSecondName;
    private String patientLastName;
    private String doctorFirstName;
    private String doctorSecondName;
    private String doctorLastName;

    public static VisitDto from(Visit visit){
        VisitDto visitDto = new VisitDto();
        visitDto.date = visit.getDate();
        visitDto.patientFirstName = visit.getPatient().getFirstName();
        visitDto.patientSecondName =  visit.getPatient().getSecondName();
        visitDto.patientLastName =  visit.getPatient().getLastName();
        visitDto.doctorFirstName = visit.getDoctor().getFirstName();
        visitDto.doctorSecondName = visit.getDoctor().getSecondName();
        visitDto.doctorLastName = visit.getDoctor().getLastName();
        visitDto.description = visit.getDescription();
        return visitDto;
    }




}
