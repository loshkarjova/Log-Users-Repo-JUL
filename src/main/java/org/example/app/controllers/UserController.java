package org.example.app.controllers;

import lombok.RequiredArgsConstructor;
import org.example.app.starter.AppStarter;
import org.example.app.utils.Constants;
import org.example.app.views.UserView;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class UserController {
    private final UserView userView;
    private final Supplier<String> supplier;

    public void processRequest() {
        try {
            String user = supplier.get();
            userView.getResultOutput(user);
        } catch (Exception e) {
            userView.getResultOutput(Constants.DB_ABSENT_MSG);
        }
        AppStarter.startApp();
    }
}
