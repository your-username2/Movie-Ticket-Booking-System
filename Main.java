import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize User Manager and Scanner
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        while (true) {
            // Show the main menu
            System.out.println("\n--- Movie Ticket Booking System ---");
            System.out.println("1. Register as User");
            System.out.println("2. Login");
            System.out.println("3. Admin: Register a New Admin");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Regular User Registration
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();

                    userManager.registerUser(regUsername, regPassword, "User");
                    break;

                case 2:
                    // Login
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    currentUser = userManager.loginUser(loginUsername, loginPassword);

                    // Differentiate user experience
                    if (currentUser != null) {
                        if (currentUser.getRole().equalsIgnoreCase("Admin")) {
                            adminMenu(scanner, userManager, currentUser);
                        } else {
                            userMenu(scanner, currentUser);
                        }
                    }
                    break;

                case 3:
                    // Register Admin (must be logged in as Admin)
                    if (currentUser != null && currentUser.getRole().equalsIgnoreCase("Admin")) {
                        System.out.print("Enter new Admin username: ");
                        String newAdminUsername = scanner.nextLine();
                        System.out.print("Enter new Admin password: ");
                        String newAdminPassword = scanner.nextLine();

                        userManager.addAdmin(currentUser, newAdminUsername, newAdminPassword);
                    } else {
                        System.out.println("Only logged-in Admins can create new Admin accounts.");
                    }
                    break;

                case 4:
                    // Exit
                    System.out.println("Thank you for using the Movie Ticket Booking System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Separate method for User menu
    private static void userMenu(Scanner scanner, User user) {
        // User-specific menu for booking actions
        // [Add User options like view bookings, make a booking, etc.]
    }

    // Separate method for Admin menu
    private static void adminMenu(Scanner scanner, UserManager userManager, User admin) {
        // Admin-specific menu for managing movies, showtimes, and users
        // [Add Admin options for system management]
    }
}