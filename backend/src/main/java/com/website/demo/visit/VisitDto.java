

package com.website.demo.visit;

import com.website.demo.patient.Patient;
import com.website.demo.patient.PatientDto;
import com.website.demo.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
@Data
@AllArgsConstructor
public class VisitDto {

    public LocalDateTime date;
    public String patientName;
    public String description;


}
