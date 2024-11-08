package movies.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movies.common.type.MovieType;
import movies.rental.strategy.*;

@Getter
@Setter
@NoArgsConstructor
public class Rental {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("movie_id")
    private Long movieId;

    @JsonProperty("rental_movie_type")
    private MovieType rentalMovieType;

    @JsonProperty("days_rented")
    private int daysRented;

    @JsonIgnore
    private RentalStrategy strategy;

    public Rental(Long id, Long customerId, Long movieId, MovieType rentalMovieType, int daysRented) {
        this.id = id;
        this.customerId = customerId;
        this.movieId = movieId;
        this.rentalMovieType = rentalMovieType;
        this.daysRented = daysRented;
        this.strategy = RentalStrategyFactory.createRentalStrategy(rentalMovieType);
    }

    public double calculateAmount() {
        return strategy.calculateAmount(daysRented);
    }

    public int calculateFrequentRenterPoints() {
        return strategy.calculateFrequentRenterPoints(daysRented);
    }
}