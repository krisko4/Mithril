package com.website.demo.login;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class LoginControllerUnitTest {


    @Test
    void login() {
        LoginService loginService = Mockito.mock(LoginService.class);
        LoginController loginController = new LoginController(loginService);
    }
}