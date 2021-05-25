package com.website.demo.referral;

import lombok.Data;

@Data
public class ReferralRequest {

    private String dispensary;
    private String speciality;
    private String reason;
    private String priority;
    private int code;


}
