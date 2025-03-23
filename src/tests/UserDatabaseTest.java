package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.controller.User;
import main.controller.UserDatabase;

public class UserDatabaseTest {
    User user = new User("testUser", "testPassword123");
    UserDatabase userDatabase;
    String filePath = "test_users.csv"; // use a different DB so we can erase it every time a test is run for consistent results

    @BeforeEach
    void setUp() {
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(new byte[0]); // Write an empty byte array to the file to erase it
        } catch (IOException e) {
            e.printStackTrace();
        }
        userDatabase = new UserDatabase(filePath);
    }

    @Test
    void testAddUser() {
        assertEquals(userDatabase.getUsers().size(), 0);
        userDatabase.addUser(user);
        assertEquals(userDatabase.getUsers().size(), 1);
    }

    @Test
    void testGetUser() {
        userDatabase.addUser(user);
        String wrongUsername = "userTest";
        String wrongPassword = "testPassword";
        String rightUsername = "testUser";
        String rightPassword = "testPassword123";
        assertNull(userDatabase.getUser(wrongUsername, rightPassword));
        assertNull(userDatabase.getUser(rightUsername, wrongPassword));
        assertNull(userDatabase.getUser(wrongUsername, wrongPassword));
        assertNotNull(userDatabase.getUser(rightUsername, rightPassword));
    }
}
