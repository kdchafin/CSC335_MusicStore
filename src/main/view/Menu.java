package main.view;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu { 
    private static Scanner in;
    protected String menu;
    protected final HashMap<String, Runnable> options;
    protected final HashMap<Integer, Runnable> numberedOptions;
    protected Runnable defaultOption;

    public Menu(String menu) {
        this.menu = menu;
        options = new HashMap<String, Runnable>();
        numberedOptions = new HashMap<Integer, Runnable>();
        defaultOption = () -> { System.out.println("Hello World. You Should Never See This."); executeMenu(); };
    }
    
    @Override
    public String toString() {
        return menu;
    }


    public void addOption(int number, String option, Runnable function) {
        options.put(option, function);
        numberedOptions.put(number, function);
    }

    public void executeOption(String option) {
        Runnable function = options.get(option);
        if(function != null) {
            function.run();
        } else {
            System.out.println("Function not found: " + option);
            executeMenu();
        }
    }

    public void executeOption(int number) {
        Runnable function = numberedOptions.get(number);
        if(function != null) {
            function.run();
        } else {
            System.out.println("Function not found: " + number);
            executeMenu();
        }
    }

    public void executeMenu() {
        Scanner in = getScanner();
        System.out.println("---------------");
        System.out.println(menu);
        System.out.println("---------------");
        String input = in.nextLine();
        
        if(input.equals("")) {
            defaultOption.run();
        }
        int chosenOption = 0;
        try {
            chosenOption = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Function not found: " + input);
            executeMenu();
        }
        executeOption(chosenOption);
    }

    public static Scanner getScanner() {
        if(in == null) {
            in = new Scanner(System.in);
        }
        return in;
    }
}
