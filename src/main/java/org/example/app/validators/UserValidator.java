package org.example.app.validators;

import lombok.AllArgsConstructor;
import org.example.app.entities.User;
import org.example.app.entities.UserDto;
import org.example.app.repositories.UserRepository;
import org.example.app.utils.Constants;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public Map<String, String> validateUserOnCreate(User user) {
        Map<String, String> errors = new HashMap<>();

        if (user.getName().trim().isEmpty())
            errors.put("name", Constants.INPUT_REQ_MSG);
        if (!PhoneValidator.isPhoneValid(user.getPhone().trim()))
            errors.put("phone", Constants.WRONG_PHONE_MSG);
        if (!EmailValidator.isEmailValid(user.getEmail().trim()))
            errors.put("email", Constants.WRONG_EMAIL_MSG);
        return errors;

    }

    public Map<String, String> validateOnUpdate(UserDto userDto) {
        Map<String, String> map = validateId(userDto.getId());
        Map<String, String> errors = new HashMap<>(map);
        if (!PhoneValidator.isPhoneValid(userDto.getPhone().trim()))
            errors.put("phone", Constants.WRONG_PHONE_MSG);
        if (!EmailValidator.isEmailValid(userDto.getEmail().trim()))
            errors.put("email", Constants.WRONG_EMAIL_MSG);
        return errors;
    }

    public Map<String, String> validateId(String id) {
        Map<String, String> errors = new HashMap<>();
        int idInt = 0;
        try {
            idInt = Integer.parseInt(id);
        } catch (Exception e) {
            errors.put("id", e.getMessage());
        }
        if (IdValidator.isIdValid(id) || idInt < 0) {
            errors.put("id", Constants.WRONG_ID_MSG);
        }
        if (!userRepository.isIdExists(idInt)) {
            errors.put("id", Constants.ID_NO_EXISTS_MSG);
        }
        return errors;

    }
}
