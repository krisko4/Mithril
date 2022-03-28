package com.website.demo.registration;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ConfirmationRequest {
    private String email;
    private String firstName;
}
