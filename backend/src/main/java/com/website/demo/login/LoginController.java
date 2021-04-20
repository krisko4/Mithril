package com.website.demo.login;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) throws Exception {
        return loginService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }


}
