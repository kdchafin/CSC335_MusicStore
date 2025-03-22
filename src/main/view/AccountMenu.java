package main.view;

import main.model.UserDatabase;
import main.model.User;
import java.util.Scanner;

public class AccountMenu extends Menu {
    UserDatabase userDatabase = new UserDatabase();
    public AccountMenu() {
        super("""
        [1] Login
        [2] Create Account
        """.trim());
        colorizeBrackets();
        defaultOption = () -> { System.exit(0); };
        addOption(1, "login", () -> { login();});
        addOption(2, "create account", () -> { createAccount(); });
    }
    
    private void login() {
        Scanner in = Menu.getScanner(); 
        System.out.println("Please enter your username: ");
        String username = in.nextLine();
        System.out.println("Please enter your password: ");
        String password = in.nextLine();
        User user = userDatabase.getUser(username, password);
        if (user != null) {
           MainMenu mainMenu = new MainMenu(this, user);
           mainMenu.executeMenu();
        } else {
            System.out.println("Invalid username or password.");
            executeMenu();
        }
    }

    private void createAccount() {
        Scanner in = Menu.getScanner();
        System.out.println("Please enter a username: ");
        String username = in.nextLine();
        System.out.println("Please enter a password: ");
        String password = in.nextLine();
        User user = new User(username, password);
        userDatabase.addUser(user);
        System.out.println(GREEN + "Account created successfully." + RESET);
        MainMenu mainMenu = new MainMenu(this, user);
        mainMenu.executeMenu();
    }
}