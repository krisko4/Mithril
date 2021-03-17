package com.website.demo.emails;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Feedback {

    @NotNull
    private String name;
    @Email
    private String email;
    @Min(10)
    private String feedback;

}
