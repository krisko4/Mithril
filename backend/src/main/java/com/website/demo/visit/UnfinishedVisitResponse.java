package com.website.demo.visit;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnfinishedVisitResponse extends VisitResponse {

    private VisitDto visit;
}
