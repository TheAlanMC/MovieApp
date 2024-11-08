package movies.rental.reports;

import lombok.NoArgsConstructor;
import movies.rental.entity.Rental;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

@NoArgsConstructor
public class AmountCalculator {

    public static double calculateTotalAmount(List<Rental> rentals) {
        return rentals.stream()
                .mapToDouble(Rental::calculateAmount)
                .sum();
    }
}
