package carsharing;

import carsharing.dto.Car;
import carsharing.dto.Company;
import carsharing.dto.Customer;

import java.util.List;

public class ViewList {
    static void showCustomerList(List<Customer> customers) {
        if(customers.isEmpty()) {
            System.out.println("The customer list is empty!\n");
            Menu.mainMenu();
        } else {
            System.out.println("Customer list:");
            for(Customer customer : customers) {
                System.out.println(customer.getId() + ". " + customer.getName()); // можливо змінити id
            }
            System.out.println("0. Back");
        }
    }
    static void showManagerCompanyList(List<Company> companies) {
        if(companies.isEmpty()) {
            System.out.println("The company list is empty!");
            Menu.managerMenu();
        } else {
            int i = 1;
            System.out.println("Choose a company:");
            for(Company company : companies) {
                System.out.println(i++ + ". " + company.getName());
            }
            System.out.println("0. Back");
        }
    }

    static void showCustomerCompanyList(List<Company> companies) {
        if(companies.isEmpty()) {
            System.out.println("The company list is empty!");
            Menu.managerMenu();
        } else {
            int i = 1;
            System.out.println("Choose a company:");
            for(Company company : companies) {
                System.out.println(i++ + ". " + company.getName());
            }
            System.out.println("0. Back");
        }
    }

    static void showCustomerCarList(List<Car> cars, int companyId) {
        if(cars.isEmpty()) {
            System.out.println("The car list is empty!\n");
            Menu.companyMenu(companyId);
        } else {
            System.out.println("Choose a car:");
            int i = 1;
            for (Car car : cars) {
                System.out.println(i++ + ". " + car.getName());
            }
            System.out.println("0. Back");
        }
    }
}
