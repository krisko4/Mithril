package com.website.demo.referral;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ReferralService {

    private final ReferralRepository referralRepository;



}
