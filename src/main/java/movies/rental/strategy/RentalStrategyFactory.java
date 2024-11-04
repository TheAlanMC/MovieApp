package movies.rental.strategy;

import movies.movie.MovieType;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class RentalStrategyFactory {
    public static RentalStrategy createRentalStrategy(MovieType type) {
        return switch (type) {
            case NEW_RELEASE -> new NewReleaseRentalStrategy();
            case CHILDREN -> new ChildrenRentalStrategy();
            case REGULAR -> new RegularRentalStrategy();
        };
    }
}

