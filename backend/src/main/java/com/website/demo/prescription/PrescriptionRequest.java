package com.website.demo.prescription;

import com.website.demo.medication.MedicationRequest;
import lombok.Data;

@Data
public class PrescriptionRequest {

    private int code;
    private MedicationRequest[] medications;

}
