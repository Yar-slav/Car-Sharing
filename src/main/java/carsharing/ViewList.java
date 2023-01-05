package carsharing;

import carsharing.dto.Car;
import carsharing.dto.Company;
import carsharing.dto.Customer;

import java.util.List;

public class ViewList {

    static boolean showCustomerListIfNotEmpty(List<Customer> customers) {
        if(!customers.isEmpty()) {
            int i = 1;
            System.out.println("Customer list:");
            for (Customer customer : customers) {
                System.out.println(i++ + ". " + customer.getName());
            }
            System.out.println("0. Back");
            return true;
        }
        return false;
    }
    static boolean showCompanyListIfNotEmpty(List<Company> companies) {
        if(!companies.isEmpty()) {
            int i = 1;
            System.out.println("Choose a company:");
            for (Company company : companies) {
                System.out.println(i++ + ". " + company.getName());
            }
            System.out.println("0. Back");
            return true;
        }
        return false;
    }

    static boolean showCustomerCarListIfNotEmpty(List<Car> cars) {
        if(!cars.isEmpty()) {
            System.out.println("Choose a car:");
            int i = 1;
            for (Car car : cars) {
                System.out.println(i++ + ". " + car.getName());
            }
            System.out.println("0. Back");
            return true;
        }
        return false;
    }
}
