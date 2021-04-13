package com.website.demo.validation;

public class PasswordValidator {

    public static boolean isValid(String password) {
        if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
            throw new IllegalStateException("Password is invalid.");
        }
        return true;

    }

    public static boolean areEqual(String password1, String password2) {
        if (!password1.equals(password2)) {
            throw new IllegalStateException("Passwords are different.");
        }
        return true;
    }
}
