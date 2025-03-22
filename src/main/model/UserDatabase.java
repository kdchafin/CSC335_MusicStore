package main.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class UserDatabase {
    private String filePath;
    private HashMap<String, User> users = new HashMap<>();
    
    // provide a constructor with no parameters that uses default file path
    public UserDatabase() {
        this("users.csv");
    }

    // provide a second  constructor that takes a file path as a parameter for testing
    public UserDatabase(String filePath) {
        this.filePath = filePath;
        initializeDatabase();
    }
    private void initializeDatabase() {
        Path path = Paths.get(filePath); 
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedReader reader = null;
        try { 
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(","); // username,password,salt format.

                User user = new User(userInfo[0], userInfo[1], true);
                users.put(user.getUsername(), user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(user.getUsername() + "," + user.getHashedPassword() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        users.put(user.getUsername(), user);
    }
    
    //TODO: this is probably useless but i use it in the test case
    public HashMap<String, User> getUsers() {
        // make a copy of the map to avoid escaping reference - user is immutable so no need for deep copy
        return new HashMap<String, User>(users);  
    }

    public User getUser(String username, String password) {
        User user = users.getOrDefault(username, null);
        if(user == null) {
            return null; // cannot just return user because we need to do password validation
        }
        String hashedPassword = user.getHashedPassword();
        String hashPassword = User.hashPassword(password);
        if(hashPassword.equals(hashedPassword)) {
            return user; // return the user if the password is correct, then we can get the user's library.
        } else {
            return null;
        }
    }
}
