package org.example.app.controllers;

import lombok.RequiredArgsConstructor;
import org.example.app.exceptions.OptionException;
import org.example.app.services.FilterChoiceService;
import org.example.app.starter.AppStarter;
import org.example.app.views.AppView;

@RequiredArgsConstructor
public class AppController {
    private final AppView appView;
    private final FilterChoiceService filterChoiceService;

    public void runApp() {
        try {
            filterChoiceService.filterChoice(appView.chooseOption());
        } catch (OptionException e) {
            AppStarter.startApp();
        }
    }
}
