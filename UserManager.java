import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    // New method to directly add users (e.g., default admin)
    public void addUserDirectly(User user) {
        users.add(user);
    }

    public boolean registerUser(String username, String password, String role) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already taken. Please choose a different username.");
                return false;
            }
        }

        if (role.equalsIgnoreCase("Admin")) {
            System.out.println("Only existing Admins can create new Admin accounts.");
            return false;
        }

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

    public boolean addAdmin(User admin, String username, String password) {
        if (admin != null && admin.getRole().equalsIgnoreCase("Admin")) {
            return registerUser(username, password, "Admin");
        } else {
            System.out.println("Only Admins can add new Admin accounts.");
            return false;
        }
    }
}