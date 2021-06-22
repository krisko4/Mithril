package com.website.demo.visit.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.website.demo.medication.Medication;
import com.website.demo.medication.MedicationRequest;
import com.website.demo.patient.Patient;
import com.website.demo.prescription.PrescriptionRequest;
import com.website.demo.referral.Referral;
import com.website.demo.referral.ReferralRequest;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FinishedVisitRequest {

    private Long patientId;
    private Long doctorId;
    private String interview;
    private String research;
    private ReferralRequest[] referrals;
    private PrescriptionRequest prescriptionRequest;
    private Long[] medicationIds;
    private int duration;
    private String date;

}
