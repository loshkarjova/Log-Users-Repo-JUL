package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.database.DBConn;
import org.example.app.entities.User;
import org.example.app.entities.UserDto;
import org.example.app.repositories.UserRepository;
import org.example.app.utils.Constants;
import org.example.app.validators.UserValidator;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserValidator validator;
    private final Connection connection = DBConn.connect();
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public String createUser(User user) {
        if (connection == null) {
            LOGGER.log(Level.SEVERE, Constants.NO_DS_CONNECTION);
            return Constants.LOG_DB_ERROR_MSG;
        }
        Map<String, String> errors = validator.validateUserOnCreate(user);
        if (errors.size() > 0) {
            LOGGER.log(Level.WARNING, Constants.LOG_DATA_INPTS_WRONG_MSG);
            return errors.toString();
        }
        return userRepository.createUser(user);
    }

    public String deleteUserById(String id) {
        Map<String, String> errors = validator.validateId(id);
        if (errors.size() > 0) {
            LOGGER.log(Level.WARNING, Constants.LOG_DATA_INPTS_WRONG_MSG);
            return errors.toString();
        }
        return userRepository.deleteUser(Integer.parseInt(id));
    }

    public String getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        if (users != null || !users.isEmpty()) {
            AtomicInteger atomicInteger = new AtomicInteger();
            StringBuilder stringBuilder = new StringBuilder();
            users.forEach(user ->
                    stringBuilder.append(atomicInteger.incrementAndGet())
                            .append(") id ")
                            .append(user.getId())
                            .append(", ")
                            .append(user.getName())
                            .append(", ")
                            .append(user.getPhone())
                            .append(", ")
                            .append(user.getEmail())
                            .append("\n")
            );
            return stringBuilder.toString();
        } else {
            LOGGER.log(Level.WARNING, Constants.LOG_DATA_ABSENT_MSG);
            return Constants.DATA_ABSENT_MSG;
        }
    }

    public String updateUser(UserDto userDto) {
        Map<String, String> errors = validator.validateOnUpdate(userDto);
        if (errors.size() > 0) {
            LOGGER.log(Level.WARNING, Constants.LOG_DATA_INPTS_WRONG_MSG);
            return errors.toString();
        }
        return userRepository.updateUser(userDto);
    }
}
