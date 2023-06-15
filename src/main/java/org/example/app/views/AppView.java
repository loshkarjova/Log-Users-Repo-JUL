package org.example.app.views;

import lombok.RequiredArgsConstructor;
import org.example.app.starter.AppStarter;
import org.example.app.utils.Constants;

import java.util.InputMismatchException;
import java.util.Scanner;

@RequiredArgsConstructor
public class AppView {

    private Scanner scanner;
    private int option;

    public int chooseOption() {
        scanner = new Scanner(System.in);
        showMenu();
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println(Constants.INCORRECT_VALUE_MSG);
            AppStarter.startApp();
        }
        return option;
    }

    private void showMenu() {
        System.out.print("""
                                
                ______ MENU ___________
                1 - Create an user.
                2 - View users.
                3 - Update the user's phone number.
                4 - Delete user.
                0 - Close the App.
                """);
    }

    public void getOutput(int choice, String output) {
        if (choice == 0) System.out.println(output);
        scanner.close();
        System.exit(0);
    }
}
