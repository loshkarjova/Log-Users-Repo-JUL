package org.example.app.validators;

import org.example.app.utils.Constants;

public class IdValidator {
    public static boolean isIdValid(String id) {
        if (id.isEmpty()) {
            return false;
        }
        return id.matches(Constants.ID_RGX);
    }
}
