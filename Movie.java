public class Movie {
    private String title;
    private String genre;
    private int duration; // in minutes
    private double rating;

    public Movie(String title, String genre, int duration, double rating) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void getDetails() {
        System.out.println("Title: " + title + ", Genre: " + genre + ", Duration: " + duration + " mins, Rating: " + rating);
    }
}