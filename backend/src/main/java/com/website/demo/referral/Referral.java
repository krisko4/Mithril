package com.website.demo.referral;

import com.website.demo.dispensary.Dispensary;
import com.website.demo.visit.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Referral(Dispensary dispensary,
                    Visit visit,
                    String reason,
                    int priority) {
        this.dispensary = dispensary;
        this.visit = visit;
        this.reason = reason;
        this.priority = priority;

    }
}
