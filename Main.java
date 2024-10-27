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

    // User-specific menu for booking actions
    private static void userMenu(Scanner scanner, User user) {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Booking History");
            System.out.println("2. Make a Booking");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int userChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (userChoice) {
                case 1:
                    // View Booking History
                    user.viewBookingHistory();
                    break;

                case 2:
                    // Make a Booking
                    // Step 1: Create a Movie instance (example movie)
                    Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 8.8);

                    // Step 2: Create a Showtime for that movie
                    Showtime showtime1 = new Showtime(movie1, "18:00", 10); // 10 seats available

                    // Show details before booking
                    movie1.getDetails();
                    showtime1.showDetails();

                    // Step 3: Book seats using the Booking system
                    List<Seat> seatsToBook = new ArrayList<>();
                    seatsToBook.add(showtime1.getAvailableSeats().get(0)); // Seat 1
                    seatsToBook.add(showtime1.getAvailableSeats().get(1)); // Seat 2

                    Booking booking = new Booking(user, showtime1, seatsToBook);

                    // Step 4: Let the user choose a payment method
                    Payment payment = selectPaymentMethod(scanner, user);

                    // Proceed with the booking if a valid payment method was chosen
                    if (payment != null) {
                        booking.confirmBooking(payment);
                    }
                    break;

                case 3:
                    // Cancel a Booking
                    System.out.println("Canceling the latest booking...");
                    if (!user.getBookingHistory().isEmpty()) {
                        Booking lastBooking = user.getBookingHistory().get(user.getBookingHistory().size() - 1);
                        lastBooking.cancelBooking();
                    } else {
                        System.out.println("No bookings to cancel.");
                    }
                    break;

                case 4:
                    // Logout
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Admin-specific menu for managing the system
    private static void adminMenu(Scanner scanner, UserManager userManager, User admin) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Register a New Admin");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1:
                    // Register New Admin
                    System.out.print("Enter new Admin username: ");
                    String newAdminUsername = scanner.nextLine();
                    System.out.print("Enter new Admin password: ");
                    String newAdminPassword = scanner.nextLine();
                    userManager.addAdmin(admin, newAdminUsername, newAdminPassword);
                    break;

                case 2:
                    // Logout
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Modular method for selecting a payment method
    private static Payment selectPaymentMethod(Scanner scanner, User user) {
        System.out.println("\nChoose Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Wallet");
        System.out.print("Enter your choice: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Payment payment;
        switch (paymentChoice) {
            case 1:
                payment = new CreditCardPayment(20.00, "1234-5678-9012-3456", user.getUsername());
                break;
            case 2:
                System.out.print("Enter PayPal email: ");
                String email = scanner.nextLine();
                payment = new PayPalPayment(20.00, email);
                break;
            case 3:
                System.out.print("Enter Wallet ID: ");
                String walletID = scanner.nextLine();
                payment = new WalletPayment(20.00, walletID);
                break;
            default:
                System.out.println("Invalid choice, payment canceled.");
                payment = null;
        }

        return payment;
    }
}