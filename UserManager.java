import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    // Register a new user
    public boolean registerUser(String username, String password) {
        // Check if username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already taken. Please choose a different username.");
                return false;
            }
        }
        // If not, create and add new user
        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("User registered successfully: " + username);
        return true;
    }

    // Login an existing user
    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                System.out.println("Login successful! Welcome " + username);
                return user;
            }
        }
        System.out.println("Invalid username or password. Please try again.");
        return null;
    }

    // Helper method to print all users (for testing)
    public void listAllUsers() {
        System.out.println("Registered Users:");
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }
}