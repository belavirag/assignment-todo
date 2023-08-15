package dev.belavirag.assignment.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppUserTest {
    AppUser getExample() {
        return new AppUser("user", "password", AppRole.ROLE_APP_USER);
    }

    @Test
    void setUsername() {
        AppUser user = getExample();
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            user.setUsername(null);
        });
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("");
        });

        assertEquals("username cannot be null!", thrown.getMessage());
        assertEquals("username cannot be empty!", thrown2.getMessage());
    }

    @Test
    void setPassword() {
        AppUser user = getExample();
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            user.setPassword(null);
        });
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("");
        });

        assertEquals("password cannot be null!", thrown.getMessage());
        assertEquals("password cannot be empty!", thrown2.getMessage());
    }

    @Test
    void setRole() {
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            AppUser user = getExample();
            user.setRole(null);
        });

        assertEquals("role cannot be null!", thrown.getMessage());
    }

    @Test
    void hashCodeAndEquals() {
        AppUser user1 = getExample();
        user1.setPassword("Something Else"); // ignore password
        AppUser user2 = getExample();
        AppUser user3 = new AppUser("Someone", "hunter2", AppRole.ROLE_APP_USER);

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
}