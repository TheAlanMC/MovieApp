package movies;

import lombok.extern.slf4j.Slf4j;
import movies.customer.entity.Customer;
import movies.customer.service.CustomerService;
import movies.rental.entity.Rental;
import movies.rental.reports.Statement;
import movies.rental.service.RentalService;

import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        // Just for testing purposes
        CustomerService customerService = CustomerService.getInstance();
        RentalService rentalService = RentalService.getInstance();

        List<Customer> customers = customerService.getCustomers();

        try {
            rentalService.addRentalByCustomerIdAndMovieId(1L,1L, 1L, 5);
            rentalService.addRentalByCustomerIdAndMovieId(2L,1L, 2L, 1);
            rentalService.addRentalByCustomerIdAndMovieId(3L,1L, 3L, 3);
            rentalService.addRentalByCustomerIdAndMovieId(4L,2L, 1L, 5);
            rentalService.addRentalByCustomerIdAndMovieId(5L,2L, 2L, 1);
            rentalService.addRentalByCustomerIdAndMovieId(6L,3L, 3L, 3);

            // Generate statements for customers
            for (Customer customer : customers) {
                List<Rental> customerRentals = rentalService.getRentalsByCustomerId(customer.getId());
                if (!customerRentals.isEmpty()) {
                    log.info("Generating statement for customer: {}", customer.getName());
                    System.out.println(new Statement(customer, customerRentals).generate());
                }
            }
        } catch (Exception e) {
            log.error("An error occurred", e);
        }
    }
}
