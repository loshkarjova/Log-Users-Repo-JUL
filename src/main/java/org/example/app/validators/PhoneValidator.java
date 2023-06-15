package org.example.app.validators;

import org.example.app.utils.Constants;

public class PhoneValidator {
    public static boolean isPhoneValid(String phone) {
        if (phone.isEmpty()) {
            return false;
        }
        return phone.matches(Constants.PHONE_RGX);
    }
}
