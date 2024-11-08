package movies.rental.reports;

import lombok.NoArgsConstructor;
import movies.rental.entity.Rental;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

@NoArgsConstructor
public class FrequentRenterPointsCalculator {

    public static int calculateTotalFrequentRenterPoints(List<Rental> rentals) {
        return rentals.stream()
                .mapToInt(Rental::calculateFrequentRenterPoints)
                .sum();
    }
}
