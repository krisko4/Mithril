package com.website.demo.validation;

public class PasswordValidator implements Validator {

    @Override
    public void validate(String password){
        if(!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
            throw new IllegalStateException("Password is invalid.");
        }

    }

    @Override
    public void checkIfEqual(String password1, String password2) {
        if(!password1.equals(password2)){
            throw new IllegalStateException("Passwords are different.");
        }
    }
}
