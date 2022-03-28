package com.website.demo.visit.response;

import com.website.demo.visit.VisitDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnfinishedVisitResponse extends VisitResponse {

    private VisitDto visit;
}
