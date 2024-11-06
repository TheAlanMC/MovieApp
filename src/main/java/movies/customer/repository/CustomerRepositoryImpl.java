package movies.customer.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import movies.customer.Customer;
import movies.customer.exception.CustomerException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class CustomerRepositoryImpl implements CustomerRepository {
    private static final String CUSTOMERS_FILE = "src/main/resources/customers.json";
    private final List<Customer> customers = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Customer> getCustomers() {
        List<Customer> currentCustomers = loadCustomersFromJson();
        customers.addAll(currentCustomers);
        return currentCustomers;
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
        saveCustomerToJson(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private List<Customer> loadCustomersFromJson() {
        try {
            if (!new File(CUSTOMERS_FILE).exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(new File(CUSTOMERS_FILE), new TypeReference<>() {});
        } catch (IOException e) {
            log.error("Error loading customers from JSON file", e);
            throw new CustomerException("Error loading customers from JSON file");
        }
    }

    private void saveCustomerToJson(Customer customer) {
        try {
            List<Customer> currentCustomers = loadCustomersFromJson();
            if (currentCustomers.stream().anyMatch(c -> c.getId().equals(customer.getId()))) {
                log.error("Customer id already exists");
                throw new CustomerException("Customer id already exists");
            }
            currentCustomers.add(customer);
            objectMapper.writeValue(new File(CUSTOMERS_FILE), currentCustomers);
        } catch (IOException e) {
            log.error("Error saving customer to JSON file", e);
            throw new CustomerException("Error saving customer to JSON file");
        }
    }
}
