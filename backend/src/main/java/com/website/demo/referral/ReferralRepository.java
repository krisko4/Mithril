package com.website.demo.referral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long> {
}
