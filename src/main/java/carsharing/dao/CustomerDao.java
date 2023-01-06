package carsharing.dao;

import carsharing.dto.Customer;
import carsharing.RentCar;
import java.util.List;

public interface CustomerDao {

    void createCustomer(String name);

    boolean addCar(int customerId, int carId);

    boolean returnedCar(int customerId);

    RentCar getRentedCar(int customerId);

    List<Customer> getAllCustomers();
}