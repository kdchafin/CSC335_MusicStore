package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.model.User;


class UserTest {

    private User user;
    private String username;
    private String password;

    @BeforeEach
    void setUp() {
        username = "testUser";
        password = "testPassword123";
        user = new User(username, password);
    }

    @Test
    void testUserCreation() {
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertNotNull(user.getHashedPassword());
        assertNotNull(user.getSalt());
        assertEquals(16, user.getSalt().length); // Check salt length
    }

    @Test
    void testPasswordEncryption() {
        String hashedPassword = user.getHashedPassword();
        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword); // Ensure the hashed password is not the same as the plain password
    }

    @Test
    void testHashingConsistency() {
        byte[] salt = user.getSalt();
        String hashedPassword1 = User.hashPassword(password, salt);
        String hashedPassword2 = User.hashPassword(password, salt);
        assertEquals(hashedPassword1, hashedPassword2); // Same input should yield the same hash
    }

    @Test
    void testDifferentPasswordsYieldDifferentHashes() {
        User anotherUser = new User("anotherUser", "anotherPassword456");
        assertNotEquals(user.getHashedPassword(), anotherUser.getHashedPassword());
    }

    @Test
    void testSaltUniqueness() {
        User anotherUser = new User("uniqueUser", "uniquePassword789");
        assertNotEquals(user.getSalt(), anotherUser.getSalt());
    }
}
