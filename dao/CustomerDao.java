package carsharing.dao;

import carsharing.dto.Customer;
import carsharing.model.RentCar;

import java.util.List;

public interface CustomerDao {
    void createCustomer(String name);
    void addCar(int customerId, int companyId, int carId);
    void returnedCar(int customerId);
    RentCar getRentedCar(int customerId);
    List<Customer> getAllCustomers();
}