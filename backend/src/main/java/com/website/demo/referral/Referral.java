package com.website.demo.referral;

import com.website.demo.dispensary.Dispensary;
import com.website.demo.specialization.Specialization;
import com.website.demo.visit.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cryptacular.spec.Spec;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="referral")
public class Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;
    private int priority;
    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
    @ManyToOne
    @JoinColumn(name = "dispensary_id")
    private Dispensary dispensary;
    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    public Referral(Dispensary dispensary,
                    Specialization specialization,
                    Visit visit,
                    String reason,
                    int priority) {
        this.specialization = specialization;
        this.dispensary = dispensary;
        this.visit = visit;
        this.reason = reason;
        this.priority = priority;


    }
}
