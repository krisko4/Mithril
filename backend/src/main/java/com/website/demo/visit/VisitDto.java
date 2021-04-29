

package com.website.demo.visit;

import com.website.demo.patient.Patient;
import com.website.demo.patient.PatientDto;
import com.website.demo.user.AppUser;
import com.website.demo.user.DoctorDto;
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
    private DoctorDto doctor;
    private PatientDto patient;
    private String reason;
    private String interview;
    private String research;
    private String referrals;
    private String medicine;

    public static VisitDto from(Visit visit){
        VisitDto visitDto = new VisitDto();
        visitDto.date = visit.getDate();
        visitDto.patient = PatientDto.from(visit.getPatient());
        visitDto.doctor = DoctorDto.from(visit.getDoctor());
        visitDto.reason = visit.getReason();
        visitDto.interview = visit.getInterview();
        visitDto.research = visit.getResearch();
        visitDto.referrals = visit.getReferrals();
        visitDto.medicine = visit.getMedicine();
        return visitDto;
    }




}
