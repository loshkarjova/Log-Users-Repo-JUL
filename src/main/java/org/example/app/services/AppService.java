package org.example.app.services;

import lombok.RequiredArgsConstructor;
import org.example.app.controllers.UserController;
import org.example.app.views.UserView;

@RequiredArgsConstructor
public class AppService {

    private final UserView userView;
    private final UserService userService;

    public void createUser() {
        new UserController(
                userView,
                () -> userService.createUser(userView.getViewOnUserCreate())).processRequest();
    }

    public void getAllUsers() {
        new UserController(
                userView,
                userService::getAllUsers).processRequest();
    }

    public void updateUser() {
        new UserController(
                userView,
                () -> userService.updateUser(userView.getViewOnUserUpdate())).processRequest();
    }

    public void deleteUser() {
        new UserController(
                userView,
                () -> userService.deleteUserById(userView.getViewOnUserDelete())).processRequest();
    }

}
