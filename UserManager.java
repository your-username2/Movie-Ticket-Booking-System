import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    public boolean registerUser(String username, String password, String role) {
        // Check if username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already taken. Please choose a different username.");
                return false;
            }
        }

        // Only allow existing Admins to create new Admin accounts
        if (role.equalsIgnoreCase("Admin")) {
            System.out.println("Only existing Admins can create new Admin accounts.");
            return false;
        }

        // Create and add new user
        User newUser = new User(username, password, role);
        users.add(newUser);
        System.out.println("User registered successfully: " + username + " (" + role + ")");
        return true;
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                System.out.println("Login successful! Welcome " + username + " (" + user.getRole() + ")");
                return user;
            }
        }
        System.out.println("Invalid username or password. Please try again.");
        return null;
    }

    // Method to add Admins by existing Admins (simulate privilege)
    public boolean addAdmin(User admin, String username, String password) {
        if (admin != null && admin.getRole().equalsIgnoreCase("Admin")) {
            return registerUser(username, password, "Admin");
        } else {
            System.out.println("Only Admins can add new Admin accounts.");
            return false;
        }
    }
}