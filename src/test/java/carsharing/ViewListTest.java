package carsharing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import carsharing.dto.Car;
import carsharing.dto.Company;
import carsharing.dto.Customer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ViewListTest {

    @Test
    void showCustomerList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Customer 1"));
        customers.add(new Customer(2, "Customer 2"));

        boolean result = ViewList.showCustomerListIfNotEmpty(customers);

        assertTrue(result);
    }

    @Test
    void showCustomerListEmpty() {
        List<Customer> customers = new ArrayList<>();
        boolean result = ViewList.showCustomerListIfNotEmpty(customers);
        assertFalse(result);
    }

    @Test
    void showCompanyList() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "Company 1"));
        companies.add(new Company(2, "Company 2"));

        boolean result = ViewList.showCompanyListIfNotEmpty(companies);
        assertTrue(result);
    }

    @Test
    void showCompanyListEmpty() {
        List<Company> companies = new ArrayList<>();

        boolean result = ViewList.showCompanyListIfNotEmpty(companies);
        assertFalse(result);
    }

    @Test
    void showCustomerCarList() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, "Car 1"));
        cars.add(new Car(2, "Car 2"));

        boolean result = ViewList.showCustomerCarListIfNotEmpty(cars);
        assertTrue(result);
    }

    @Test
    void showCustomerCarListEmpty() {
        List<Car> cars = new ArrayList<>();

        boolean result = ViewList.showCustomerCarListIfNotEmpty(cars);
        assertFalse(result);
    }
}