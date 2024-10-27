import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize User Manager and Scanner
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        // Add a default Admin account directly
        User defaultAdmin = new User("admin", "admin123", "Admin");
        userManager.addUserDirectly(defaultAdmin);

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
                    user.viewBookingHistory();
                    break;
                case 2:
                    Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 8.8);
                    Showtime showtime1 = new Showtime(movie1, "18:00", 10);

                    movie1.getDetails();
                    showtime1.showDetails();

                    List<Seat> seatsToBook = new ArrayList<>();
                    seatsToBook.add(showtime1.getAvailableSeats().get(0)); // Seat 1
                    seatsToBook.add(showtime1.getAvailableSeats().get(1)); // Seat 2

                    Booking booking = new Booking(user, showtime1, seatsToBook);
                    Payment payment = selectPaymentMethod(scanner, user);

                    if (payment != null) {
                        booking.confirmBooking(payment);
                    }
                    break;
                case 3:
                    System.out.println("Canceling the latest booking...");
                    if (!user.getBookingHistory().isEmpty()) {
                        Booking lastBooking = user.getBookingHistory().get(user.getBookingHistory().size() - 1);
                        lastBooking.cancelBooking();
                    } else {
                        System.out.println("No bookings to cancel.");
                    }
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Admin-specific menu for managing the system
    private static void adminMenu(Scanner scanner, UserManager userManager, User admin) {
        MovieManager movieManager = new MovieManager();
        ShowtimeManager showtimeManager = new ShowtimeManager();

        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Register a New Admin");
            System.out.println("2. Manage Movies");
            System.out.println("3. Manage Showtimes");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1:
                    System.out.print("Enter new Admin username: ");
                    String newAdminUsername = scanner.nextLine();
                    System.out.print("Enter new Admin password: ");
                    String newAdminPassword = scanner.nextLine();
                    userManager.addAdmin(admin, newAdminUsername, newAdminPassword);
                    break;

                case 2:
                    manageMovies(scanner, movieManager);
                    break;

                case 3:
                    manageShowtimes(scanner, showtimeManager, movieManager);
                    break;

                case 4:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to manage movies
    private static void manageMovies(Scanner scanner, MovieManager movieManager) {
        while (true) {
            System.out.println("\n--- Manage Movies ---");
            System.out.println("1. Add Movie");
            System.out.println("2. Update Movie");
            System.out.println("3. Delete Movie");
            System.out.println("4. List Movies");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter duration (in mins): ");
                    int duration = scanner.nextInt();
                    System.out.print("Enter rating: ");
                    double rating = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    movieManager.addMovie(title, genre, duration, rating);
                    break;

                case 2:
                    movieManager.listMovies();
                    System.out.print("Enter the index of the movie to update: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter new genre: ");
                    genre = scanner.nextLine();
                    System.out.print("Enter new duration (in mins): ");
                    duration = scanner.nextInt();
                    System.out.print("Enter new rating: ");
                    rating = scanner.nextDouble();
                    scanner.nextLine();
                    movieManager.updateMovie(index, title, genre, duration, rating);
                    break;

                case 3:
                    movieManager.listMovies();
                    System.out.print("Enter the index of the movie to delete: ");
                    index = scanner.nextInt();
                    scanner.nextLine();
                    movieManager.deleteMovie(index);
                    break;

                case 4:
                    movieManager.listMovies();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to manage showtimes
    private static void manageShowtimes(Scanner scanner, ShowtimeManager showtimeManager, MovieManager movieManager) {
        while (true) {
            System.out.println("\n--- Manage Showtimes ---");
            System.out.println("1. Add Showtime");
            System.out.println("2. Update Showtime");
            System.out.println("3. Delete Showtime");
            System.out.println("4. List Showtimes");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    movieManager.listMovies();
                    System.out.print("Enter the index of the movie for the showtime: ");
                    int movieIndex = scanner.nextInt();
                    scanner.nextLine();
                    Movie movie = movieManager.getMovie(movieIndex);
                    if (movie != null) {
                        System.out.print("Enter time (e.g., 18:00): ");
                        String time = scanner.nextLine();
                        System.out.print("Enter number of seats: ");
                        int seats = scanner.nextInt();
                        scanner.nextLine();
                        showtimeManager.addShowtime(movie, time, seats);
                    } else {
                        System.out.println("Invalid movie index.");
                    }
                    break;

                case 2:
                    showtimeManager.listShowtimes();
                    System.out.print("Enter the index of the showtime to update: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new time: ");
                    String time = scanner.nextLine();
                    System.out.print("Enter new number of seats: ");
                    int seats = scanner.nextInt();
                    scanner.nextLine();
                    showtimeManager.updateShowtime(index, time, seats);
                    break;

                case 3:
                    showtimeManager.listShowtimes();
                    System.out.print("Enter the index of the showtime to delete: ");
                    index = scanner.nextInt();
                    scanner.nextLine();
                    showtimeManager.deleteShowtime(index);
                    break;

                case 4:
                    showtimeManager.listShowtimes();
                    break;

                case 5:
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