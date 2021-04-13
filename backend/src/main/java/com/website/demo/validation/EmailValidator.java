package com.website.demo.validation;

public class EmailValidator implements Validator {
    @Override
    public void validate(String email) {
        if (!email.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {
            throw new IllegalStateException("E-mail is invalid.");
        }
    }

    @Override
    public void checkIfEqual(String email1, String email2) {
        if(!email1.equals(email2)){
            throw new IllegalStateException("E-mails are different.");
        }
    }
}
