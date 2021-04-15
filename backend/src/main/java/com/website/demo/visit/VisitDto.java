

package com.website.demo.visit;

import com.website.demo.patient.Patient;
import com.website.demo.patient.PatientDto;
import com.website.demo.user.AppUser;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
@Data
public class VisitDto {

    private LocalDateTime date;
    private String description;
    private AppUser doctorDto;
    private Patient patientDto;

    public VisitDto(Visit visit){

        date = visit.getDate();
        description = visit.getDescription();
        doctorDto = visit.getDoctor();
        patientDto = visit.getPatient();

    }




}
