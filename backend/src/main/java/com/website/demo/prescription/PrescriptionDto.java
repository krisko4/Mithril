package com.website.demo.prescription;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PrescriptionDto {

    private Long id;
    private String code;
    private LocalDate expirationDate;
    private String content;

    public static PrescriptionDto from(Prescription prescription) {
        PrescriptionDto dto = new PrescriptionDto();

        dto.id = prescription.getId();
        dto.code = prescription.getCode();
        dto.expirationDate = prescription.getExpirationDate();
        dto.content = prescription.getContent();

        return dto;
    }
}
