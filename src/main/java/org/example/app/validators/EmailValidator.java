package org.example.app.validators;

import org.example.app.utils.Constants;

class EmailValidator {
    public static boolean isEmailValid(String email) {
        if (email.isEmpty()) {
            return false;
        }
        return email.matches(Constants.EMAIL_RGX);
    }
}
