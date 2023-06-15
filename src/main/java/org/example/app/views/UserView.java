package org.example.app.views;

import org.example.app.entities.User;
import org.example.app.entities.UserDto;

import java.util.Scanner;

public class UserView {

    public User getViewOnUserCreate() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        user.setName(name);
        System.out.print("Enter phone in format xxx xxx-xxxx: ");
        String phone = scanner.nextLine();
        user.setPhone(phone);
        System.out.println("Enter email in format email@mail.com:  ");
        String email = scanner.nextLine();
        user.setEmail(email);
        return user;
    }

    public String getViewOnUserDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user's ID: ");
        String id = scanner.nextLine();
        return id;
    }

    public UserDto getViewOnUserUpdate() {
        Scanner scanner = new Scanner(System.in);
        UserDto userDto = new UserDto();
        System.out.print("Enter user's ID: ");
        String id = scanner.nextLine();
        userDto.setId(id);
        System.out.print("Enter new phone in format xxx xxx-xxxx: ");
        String phone = scanner.nextLine();
        userDto.setPhone(phone);
        System.out.println("Enter new email in format email@mail.com");
        String email = scanner.nextLine();
        userDto.setEmail(email);
        return userDto;
    }

    public void getResultOutput(String output) {
        System.out.println(output);
    }


}
