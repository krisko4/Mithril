package com.website.demo.registration.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Getter
@Setter
@RequestMapping("tokens")
public class ConfirmationTokenController {


    private final ConfirmationTokenService confirmationTokenService;


    public ConfirmationTokenController(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping()
    public List<ConfirmationToken> getAll(){
        return confirmationTokenService.getAll();
    }
}
