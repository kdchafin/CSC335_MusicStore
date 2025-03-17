package main.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class User {
    private String username;
    private String password;
    private String salt;
    private LibraryModel libraryModel;

    public User(String username, String password, String salt) {
        this.username = username;
        this.salt = salt;
        this.password = encryptPassword(password);
        this.libraryModel = new LibraryModel();
    }

    private String encryptPassword(String password) {
        // Generate salt and cryptographically hash the password
        if (this.salt == "") {
            this.salt = generateSalt();
        }
        String hashedPassword = hashPassword(password, this.salt);
        return hashedPassword;
    }
    
    private static String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16]; // 16 bytes salt
        sr.nextBytes(salt);
        // Convert byte array to a Base64 encoded string
        return Base64.getEncoder().encodeToString(salt);
    }
    
    // Public static method in order to use in other classes to validate log in.
    public static String hashPassword(String password, String salt) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // Convert the Base64 encoded salt back to bytes
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        md.update(saltBytes);
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public LibraryModel getLibraryModel() {
        return libraryModel;
    }
}