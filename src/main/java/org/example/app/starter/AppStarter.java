package org.example.app.starter;

import org.example.app.controllers.AppController;
import org.example.app.repositories.UserRepository;
import org.example.app.services.AppService;
import org.example.app.services.FilterChoiceService;
import org.example.app.services.UserService;
import org.example.app.validators.UserValidator;
import org.example.app.views.AppView;
import org.example.app.views.UserView;

public class AppStarter {
    public static void startApp() {
        UserView userView = new UserView();
        AppView appView = new AppView();
        UserRepository userRepository = new UserRepository();

        UserValidator userValidator = new UserValidator(userRepository);
        UserService userService = new UserService(userRepository, userValidator);
        AppService appService = new AppService(userView, userService);

        FilterChoiceService filterChoiceService = new FilterChoiceService(appView, appService);

        new AppController(appView, filterChoiceService).runApp();
    }
}
