package com.website.demo.examination;

import com.website.demo.visit.Visit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name="examination")
@NoArgsConstructor
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String interview;
    private String research;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "visit_id")
    private Visit visit;

    public Examination(String interview, String research, Visit visit){
        this.interview = interview;
        this.research = research;
        this.visit = visit;
    }
}
