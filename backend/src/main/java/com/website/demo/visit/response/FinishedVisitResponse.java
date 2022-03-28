package com.website.demo.visit.response;

import com.website.demo.examination.ExaminationDto;
import com.website.demo.prescription.PrescriptionDto;
import com.website.demo.referral.Referral;
import com.website.demo.visit.VisitDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FinishedVisitResponse extends VisitResponse {

    private VisitDto visit;
    private List<Referral> referrals;
    private PrescriptionDto prescription;
    private ExaminationDto examination;


}
