package movies;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Test");
        customer.addRental(new Rental(new Movie("Zack Snyder's Justice League", MovieType.NEW_RELEASE), 5));
        customer.addRental(new Rental(new Movie("Terminator", MovieType.REGULAR), 1));
        customer.addRental(new Rental(new Movie("Soul", MovieType.CHILDREN), 3));
        System.out.println(new Statement(customer).generate());
    }
}