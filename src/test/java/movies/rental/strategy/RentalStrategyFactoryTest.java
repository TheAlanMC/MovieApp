package movies.rental.strategy;

import movies.common.type.MovieType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chris Alan Apaza Aguilar
 */

class RentalStrategyFactoryTest {

    @Test
    void createRentalStrategyShouldReturnNewReleaseRentalStrategy() {
        RentalStrategy strategy = RentalStrategyFactory.createRentalStrategy(MovieType.NEW_RELEASE);
        assertInstanceOf(NewReleaseRentalStrategy.class, strategy);
    }

    @Test
    void createRentalStrategyShouldReturnChildrenRentalStrategy() {
        RentalStrategy strategy = RentalStrategyFactory.createRentalStrategy(MovieType.CHILDREN);
        assertInstanceOf(ChildrenRentalStrategy.class, strategy);
    }

    @Test
    void createRentalStrategyShouldReturnRegularRentalStrategy() {
        RentalStrategy strategy = RentalStrategyFactory.createRentalStrategy(MovieType.REGULAR);
        assertInstanceOf(RegularRentalStrategy.class, strategy);
    }
}