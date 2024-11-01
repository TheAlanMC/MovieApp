package movies;

public class Movie {
    private String title;
    private MovieType priceCode;

    public Movie(String title, MovieType priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public MovieType getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(MovieType priceCode) {
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }
}