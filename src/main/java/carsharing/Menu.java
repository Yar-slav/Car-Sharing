package carsharing;

import carsharing.dao.*;
import carsharing.dto.Car;
import carsharing.dto.Company;
import carsharing.dto.Customer;
import carsharing.model.RentCar;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Verification verification;

    private final DBConnection dbConnection;
    private final CompanyDao companyDao;
    private final CarDao carDao;
    private final CustomerDao customerDao;

    public Menu(DBConnection dbConnection, CompanyDao companyDao, CarDao carDao, CustomerDao customerDao) {
        this.dbConnection = dbConnection;
        this.companyDao = companyDao;
        this.carDao = carDao;
        this.customerDao = customerDao;
    }

    void mainMenu() {
        ViewMenu.showMainMenu();
        String line = scanner.nextLine();
        switch (line) {
            case "0": dbConnection.exit();
            case "1": managerMenu();
            case "2": customerList();
            case "3": createCustomers();
            default: {
                System.out.println();
                mainMenu();
            }
        }
    }

    void managerMenu() {
        ViewMenu.showManagerMenu();
        String line = scanner.nextLine();
        switch (line) {
            case "0": {
                System.out.println();
                mainMenu();
            }
            case "1": managerCompanyList();
            case "2": createCompany();
            default: managerMenu();
        }
    }

    void companyMenu(int companyId) {
        ViewMenu.showCompanyMenu();
        String line = scanner.nextLine();
        switch (line) {
            case "0": managerMenu();
            case "1": managerCarList(companyId);
            case "2": createCar(companyId);
            default: {
                System.out.println();
                companyMenu(companyId);
            }
        }
    }

    void  customerMenu(int customerId) {
        RentCarMenu rentCarMenu = new RentCarMenu(customerDao,
                new Menu(dbConnection, companyDao, carDao, customerDao));
        RentCar rentCar = customerDao.getRentedCar(customerId);
        ViewMenu.showCustomerMenu();

        String line = scanner.nextLine();
        System.out.println();
        switch (line) {
            case "0": mainMenu();
            case "1": {
                if (!rentCarMenu.isCarRented(rentCar, customerId)) {
                    customerCompanyList(customerId);
                }
            }
            case "2": {
                if (!rentCarMenu.isCarReturned(rentCar, customerId)) {
                    System.out.println("You didn't rent a car!");
                    customerMenu(customerId);
                }
            }
            case "3": {
                if (!rentCarMenu.showMyRentedCar(rentCar, customerId)) {
                    System.out.println("You didn't rent a car!");
                    customerMenu(customerId);
                }
            }
            default: {
                System.out.println();
                customerMenu(customerId);
            }
        }
    }

    private void customerList() {
        System.out.println();
        List<Customer> customers = customerDao.getAllCustomers();
        if (!ViewList.showCustomerListIfNotEmpty(customers)) {
            System.out.println("The customer list is empty!\n");
            mainMenu();
        }
        int customerId = 0;
        try {
            customerId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            customerList();
        }
        if (customerId == 0) {
            mainMenu();
        } else if (customerId > customers.size()) {
            customerList();
        }
        customerMenu(customerId);
    }

    private void managerCompanyList() {
        System.out.println();
        List<Company> companies = companyDao.getAllCompany();
        if (!ViewList.showCompanyListIfNotEmpty(companies)) {
            System.out.println("The company list is empty!");
            managerMenu();
        }

        int companyId = 0;
        try {
            companyId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            managerCompanyList();
        }
        if (companyId == 0) {
            managerMenu();
        } else if (companyId > companies.size()) {
            managerCompanyList();
        }
        Company company = companies.get(companyId - 1);
        System.out.println("\n'" + company.getName() + "' company:");
        companyMenu(company.getId());
    }

    private void customerCompanyList(int customerId) {
        System.out.println();
        List<Company> companies = companyDao.getAllCompany();
        ViewList.showCompanyListIfNotEmpty(companies);

        int companyIndex = 0;
        try {
            companyIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            customerCompanyList(customerId);
        }
        if (companyIndex == 0) {
            customerMenu(customerId);
        } else if (companyIndex > companies.size()) {
            customerCompanyList(customerId);
        }
        Company company = companies.get(companyIndex - 1);
        customerCarList(customerId, company.getId());
    }

    private void managerCarList(int companyId) {
        System.out.println();
        List<Car> cars = carDao.getAllCars(companyId);
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!\n");
            companyMenu(companyId);
        } else {
            System.out.println("Car list:");
            int i = 1;
            for (Car car : cars) {
                System.out.println(i++ + ". " + car.getName());
            }
        }
        companyMenu(companyId);
    }

    private void customerCarList(int customerId, int companyId) {
        System.out.println();
        List<Car> cars = carDao.getAllCars(companyId);
        if (!ViewList.showCustomerCarListIfNotEmpty(cars)) {
            System.out.println("The car list is empty!\n");
            companyMenu(companyId);
        }

        int carIndex = 0;
        try {
            carIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            customerCarList(customerId, companyId);
        }
        if (carIndex == 0) {
            customerCompanyList(customerId);
        }

        Car car = cars.get(carIndex - 1);
        String rentedCarName = car.getName();
        customerDao.addCar(customerId, companyId, car.getId());

        System.out.println("\nYou rented '" + rentedCarName + "'");
        customerMenu(customerId);
    }

    private void createCompany() {
        verification = new Verification(customerDao, companyDao);
        System.out.println("\nEnter the company name:");
        String companyName = scanner.nextLine();
        if (verification.isExistCompany(companyName)) {
            createCompany();
        }
        companyDao.createCompany(companyName);
        System.out.println("The company was created!");
        managerMenu();
    }

    private void createCar(int companyId) {
        System.out.println("\nEnter the car name:");
        String carName = scanner.nextLine();
        carDao.createCar(carName, companyId);
        System.out.println("The car was added!\n");
        companyMenu(companyId);
    }

    private void createCustomers() {
        verification = new Verification(customerDao, companyDao);
        System.out.println("\nEnter the customer name:");
        String customerName = scanner.nextLine();
        if (verification.isExistCustomer(customerName)) {
            createCustomers();
        }
        customerDao.createCustomer(customerName);
        System.out.println("The customer was added!\n");
        mainMenu();
    }
}
