package org.example.app.services;

import lombok.RequiredArgsConstructor;
import org.example.app.exceptions.OptionException;
import org.example.app.utils.Constants;
import org.example.app.views.AppView;

@RequiredArgsConstructor
public class FilterChoiceService {
    private final AppView appView;
    private final AppService appService;

    public void filterChoice(int choice) {
        switch (choice) {
            case 1 -> appService.createUser();
            case 2 -> appService.getAllUsers();
            case 3 -> appService.updateUser();
            case 4 -> appService.deleteUser();
            case 0 -> appView.getOutput(choice, Constants.APP_CLOSE_MSG);
            default -> {
                System.out.println(Constants.INCORRECT_VALUE_MSG);
                throw new OptionException();
            }

        }
    }

}
