package movies.customer.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import movies.common.repository.JsonRepositoryImpl;
import movies.customer.entity.Customer;
import movies.customer.exception.CustomerException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class CustomerRepositoryImpl implements CustomerRepository {
    private static CustomerRepositoryImpl instance;
    private static final String CUSTOMERS_FILE = "src/main/resources/customers.json";

    private final List<Customer> customers;
    private final JsonRepositoryImpl<Customer> jsonRepository;
    private final AtomicLong nextId;

    private CustomerRepositoryImpl(JsonRepositoryImpl<Customer> jsonRepository) {
        this.jsonRepository = jsonRepository;
        this.customers = jsonRepository.loadAll();
        this.nextId = new AtomicLong(customers.stream().mapToLong(Customer::getId).max().orElse(0) + 1);
    }

    public static CustomerRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CustomerRepositoryImpl(new JsonRepositoryImpl<>(CUSTOMERS_FILE, new TypeReference<>() {}));
        }
        return instance;
    }

    public static CustomerRepositoryImpl getInstance(JsonRepositoryImpl<Customer> jsonRepository) {
        if (instance == null) {
            instance = new CustomerRepositoryImpl(jsonRepository);
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        customer.setId(nextId.getAndIncrement());
        customers.add(customer);
        jsonRepository.saveAll(customers);
        return customer;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CustomerException("Customer not found"));
    }
}
