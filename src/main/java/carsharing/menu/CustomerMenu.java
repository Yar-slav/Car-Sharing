package carsharing.menu;

import carsharing.RentCar;
import carsharing.Verification;
import carsharing.ViewList;
import carsharing.dto.Car;
import carsharing.dto.Company;
import carsharing.dto.Customer;
import java.util.List;

public class CustomerMenu {

    public void customerMenu(int customerId, Menu menu) {
        RentCarMenu rentCarMenu = new RentCarMenu();
        RentCar rentCar = menu.getCustomerDao().getRentedCar(customerId);
        ViewMenu.showCustomerMenu();

        String line = menu.scanner.nextLine();
        System.out.println();
        switch (line) {
            case "0":
                menu.mainMenu();
            case "1": {
                if (!rentCarMenu.isCarRented(rentCar, customerId, menu)) {
                    customerCompanyList(customerId, menu);
                }
            }
            case "2": {
                if (!rentCarMenu.isCarReturned(rentCar, customerId, menu)) {
                    System.out.println("You didn't rent a car!");
                    customerMenu(customerId, menu);
                }
            }
            case "3": {
                if (!rentCarMenu.showMyRentedCar(rentCar, customerId, menu)) {
                    System.out.println("You didn't rent a car!");
                    customerMenu(customerId, menu);
                }
            }
            default: {
                System.out.println();
                customerMenu(customerId, menu);
            }
        }
    }

    void customerList(Menu menu) {
        System.out.println();
        List<Customer> customers = menu.getCustomerDao().getAllCustomers();
        if (!ViewList.showCustomerListIfNotEmpty(customers)) {
            System.out.println("The customer list is empty!\n");
            menu.mainMenu();
        }
        int customerId = 0;
        try {
            customerId = Integer.parseInt(menu.scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            customerList(menu);
        }
        if (customerId == 0) {
            menu.mainMenu();
        } else if (customerId > customers.size()) {
            customerList(menu);
        }
        customerMenu(customerId, menu);
    }

    void customerCompanyList(int customerId, Menu menu) {
        System.out.println();
        List<Company> companies = menu.getCompanyDao().getAllCompany();
        ViewList.showCompanyListIfNotEmpty(companies);

        int companyIndex = 0;
        try {
            companyIndex = Integer.parseInt(menu.scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            customerCompanyList(customerId, menu);
        }
        if (companyIndex == 0) {
            customerMenu(customerId, menu);
        } else if (companyIndex > companies.size()) {
            customerCompanyList(customerId, menu);
        }
        Company company = companies.get(companyIndex - 1);
        customerCarList(customerId, company.getId(), menu);
    }

    void customerCarList(int customerId, int companyId, Menu menu) {
        System.out.println();
        List<Car> cars = menu.getCarDao().getAllCars(companyId);
        if (!ViewList.showCustomerCarListIfNotEmpty(cars)) {
            System.out.println("The car list is empty!\n");
            menu.getCompanyMenu().companyMenu(companyId, menu);
        }

        int carIndex = 0;
        try {
            carIndex = Integer.parseInt(menu.scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            customerCarList(customerId, companyId, menu);
        }
        if (carIndex == 0) {
            customerCompanyList(customerId, menu);
        }

        Car car = cars.get(carIndex - 1);
        String rentedCarName = car.getName();
        menu.getCustomerDao().addCar(customerId, car.getId());

        System.out.println("\nYou rented '" + rentedCarName + "'");
        customerMenu(customerId, menu);
    }

    void createCustomers(Menu menu) {
        Verification verification = new Verification(menu.getCustomerDao(), menu.getCompanyDao());
        System.out.println("\nEnter the customer name:");
        String customerName = menu.scanner.nextLine();
        if (verification.isExistCustomer(customerName)) {
            createCustomers(menu);
        }
        menu.getCustomerDao().createCustomer(customerName);
        System.out.println("The customer was added!\n");
        menu.mainMenu();
    }
}
