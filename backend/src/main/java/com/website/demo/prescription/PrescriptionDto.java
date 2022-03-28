package com.website.demo.prescription;

import com.website.demo.medication.Medication;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PrescriptionDto {

    private Long id;
    private String code;
    private Set<Medication> medicationList;

    public static PrescriptionDto from(Prescription prescription) {
        PrescriptionDto dto = new PrescriptionDto();
        dto.id = prescription.getId();
        dto.code = prescription.getCode();
        dto.medicationList = prescription.getMedications();
        return dto;
    }
}
