package movies.rental.strategy;

import movies.common.type.MovieType;
import movies.rental.exception.RentalException;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class RentalStrategyFactory {
    public static RentalStrategy createRentalStrategy(MovieType type) {
        return switch (type) {
            case NEW_RELEASE -> new NewReleaseRentalStrategy();
            case CHILDREN -> new ChildrenRentalStrategy();
            case REGULAR -> new RegularRentalStrategy();
            default -> throw new RentalException("Invalid movie type");
        };
    }
}

